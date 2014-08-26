import sys
from bs4 import BeautifulSoup
import csv
from nltk.stem.lancaster import LancasterStemmer
from nltk.corpus import stopwords
cachedStopWords = stopwords.words("english")

def create_training_set(stack_overflow_f,api_f,train_f):
	methods_to_comments = dict()
	with open(api_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter=',')
		for row in reader:
			methods_to_comments[row[2]] = open(row[3],'r').read() 
			
	id_to_so_comments = dict()
	id_to_api_comments = dict()
	id_to_methods = dict()
	with open(stack_overflow_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter='\t')
		for row in reader:
			id_to_methods[row[0]] = list()
			combined_comments = ''
			id_to_so_comments[row[0]] = row[2].replace('\t',' ').replace('\n',' ')	
			methods  = eval(row[1])
			for method in methods:
				if method in methods_to_comments.keys():
					combined_comments += ' ' + methods_to_comments[method]
					id_to_methods[row[0]].append(method)	
			id_to_api_comments[row[0]] = combined_comments.replace('\t',' ').replace('\n',' ')
		
	f = open(train_f, 'w')
	counter = 0
	for i in id_to_so_comments.keys():
		print counter 
		counter += 1
		for j in id_to_so_comments.keys():
			if i == j:
				#f.write(id_to_so_comments[i] + '\t' + id_to_api_comments[i] + '\t' + '1' + '\n')
				f.write(overlapping_text(id_to_so_comments[i],id_to_api_comments[i]) + '\t' + '1' + '\n')
			else:
				f.write(overlapping_text(id_to_so_comments[i],id_to_api_comments[j]) + '\t' + '0' + '\n')
				#f.write(id_to_so_comments[i] + '\t' + id_to_api_comments[j] + '\t' + '0' + '\n')
			
def overlapping_text(text_1, text_2):
        st = LancasterStemmer()
        #cachedStopWords = stopwords.words("english")
        text_1_list = ([st.stem(word) for word in text_1.split() if word not in cachedStopWords])
        text_2_list = ([st.stem(word) for word in text_2.split() if word not in cachedStopWords])
        return jaccard_dist(text_1_list, text_2_list)

def jaccard_dist(a,b):
        a = set(a)
        b = set(b)
        union = a.union(b)
        inter = a.intersection(b)
        len_union = float(len(union))
        len_inter = float(len(inter))
        try:
                jsim = len_inter/abs(len_union-len_inter)
        except ZeroDivisionError:
                jsim = 0
        jdist = 1-jsim
        return str(jdist)

if __name__ == "__main__":
	create_training_set(sys.argv[1],sys.argv[2],sys.argv[3])
	
