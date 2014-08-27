import sys
from bs4 import BeautifulSoup
import csv
from nltk.stem.lancaster import LancasterStemmer
from nltk.corpus import stopwords
cachedStopWords = stopwords.words("english")
import nltk
import nltk.data, nltk.tag
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
st = LancasterStemmer()
from sklearn.metrics.pairwise import cosine_similarity

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
			if row[2] != '':
				id_to_so_comments[row[0]] = row[2]
			methods  = eval(row[1])
			for method in methods:
				if method in methods_to_comments.keys():
					combined_comments += ' ' + methods_to_comments[method]
					id_to_methods[row[0]].append(method)	
			if combined_comments != '':
				id_to_api_comments[row[0]] = combined_comments
	
	vocab = set()
	for i in id_to_so_comments.keys():
		text_set = ([word for word in id_to_so_comments[i].split() if word not in cachedStopWords])
		vocab = vocab.union(set(text_set))
	for i in id_to_api_comments.keys():
                text_set = ([word for word in id_to_api_comments[i].split() if word not in cachedStopWords])
                vocab = vocab.union(set(text_set))
	
	tf_idf_vectorizer = CountVectorizer(binary=True,
           stop_words='english', vocabulary=list(vocab))

	'''
	tf_idf_vectorizer = TfidfVectorizer(sublinear_tf=True,  ngram_range=(1,2), 
           stop_words='english', vocabulary=list(vocabulary), )
	'''
	tf_idf_vectorizer.fit(id_to_so_comments.values()+id_to_api_comments.values())
	'''	
	count_vectorizer_binary = CountVectorizer(binary=True,
           stop_words='english', vocabulary=list(vocabulary))

	count_vectorizer = CountVectorizer(binary=False,
           stop_words='english', vocabulary=list(vocabulary))
	'''
	f = open(train_f, 'w')
	counter = 0
	
	#tagger = nltk.data.load(nltk.tag._POS_TAGGER)
	completed_list = list()
	for i in id_to_so_comments.keys():
		vec_i = tf_idf_vectorizer.transform([id_to_so_comments[i]])
		print counter 
		counter += 1
		#nouns_1, verbs_1 = filter_pos(id_to_so_comments[i],tagger)
		for j in id_to_api_comments.keys():
			if i+j in completed_list or j+i in completed_list:
				#print 'skipped'
				pass
			else:
				vec_j = tf_idf_vectorizer.transform([id_to_api_comments[j]])
				#nouns_2, verbs_2 = filter_pos(id_to_api_comments[j],tagger)
				feature_5 = 1 - cosine_similarity(vec_i,vec_j)[0][0]
				#print feature_1
				feature_1 = overlapping_text(id_to_so_comments[i],id_to_api_comments[j])
				feature_2, feature_3, feature_4 = compareJavaDocTags(id_to_api_comments[j], id_to_so_comments[i])
				if (feature_1) >  .94:
					pass
					#print 'here'
				elif jaccard_dist(id_to_methods[i],id_to_methods[j]) < .75:
					#print i
					#print j
					f.write(str(feature_1) + '\t' + str(feature_2)+ '\t' + str(feature_3)+'\t' + str(feature_4)+'\t'+ str(feature_5) + '\t'+ '1' + '\n')
					#f.write(str(feature_1) +'\t' + '1' + '\n')
					#f.write(id_to_so_comments[i] + '\t' + id_to_api_comments[i] + '\t' + '1' + '\n')
					#f.write(str(feature_1) + '\t' + str(jaccard_dist(nouns_1,nouns_2))+ '\t' + str(jaccard_dist(verbs_1,verbs_2))+'\t'+ '1' + '\n')
				else:
					f.write(str(feature_1) + '\t' + str(feature_2)+ '\t' + str(feature_3)+'\t' + str(feature_4)+'\t'+str(feature_5) + '\t'+ '0' + '\n')
					#f.write(str(feature_1) +'\t' + '0' + '\n')
					#f.write(str(feature_1) + '\t' + str(jaccard_dist(nouns_1,nouns_2))+ '\t' + str(jaccard_dist(verbs_1,verbs_2))+'\t' + '0' + '\n')
					#f.write(id_to_so_comments[i] + '\t' + id_to_api_comments[j] + '\t' + '0' + '\n')
			completed_list.append(i+j)
	f.close()	

def compareJavaDocTags(text_comments, text_stackoverflow):
	at_params = list()
	at_return = list()
	first_line = list()
	counter = 1
	for line in text_comments.split('\n'):
		if('@param' in  line):
			line.replace('@param','')
			at_params+=(nltk.word_tokenize(line))	
		if('@return' in line):
			line.replace('@return','')
			at_return+=(nltk.word_tokenize(line))      
		if(counter < 3) and (line.strip() !='/**'): 
                        first_line+=(nltk.word_tokenize(line))
		counter += 1
	tokens_so = nltk.word_tokenize(text_stackoverflow)
	return normalized_frequency(at_params, tokens_so), normalized_frequency(at_return, tokens_so), normalized_frequency(first_line, tokens_so)

def normalized_frequency(a,b):
	#print a
	a = set(a)
	b = set(b)
	#union = a.union(b)
	len_inter = float(len(a.intersection(b)))
	len_smaller_set = float(len(a))
	try:
		ret_value = len_inter/len_smaller_set 
	except ZeroDivisionError:
		ret_value = 1
	return 1-ret_value
		
def filter_pos(text,tagger):
	#st = LancasterStemmer()	
	tokens = nltk.word_tokenize(text)
	tagged = tagger.tag(tokens)
	nouns = list()
	verbs = list()
        for (word, tag) in tagged:
		if tag.startswith('N'):
			nouns.append(st.stem(word))
                elif tag.startswith('V'):
        		verbs.append(st.stem(word))
	return nouns,verbs

def overlapping_text(text_1, text_2):
	#print text_2

	#st = LancasterStemmer()
        #cachedStopWords = stopwords.words("english")
        # TODO: Check this
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
                jsim = len_inter/len_union
        except ZeroDivisionError:
                jsim = 0
        jdist = 1-jsim
        return jdist

if __name__ == "__main__":
	create_training_set(sys.argv[1],sys.argv[2],sys.argv[3])
	
