'''
Create features and write to CSV to use with classifier
'''

from sklearn import metrics
from operator import itemgetter
from sklearn.metrics import classification_report
import csv,sys,os
import urllib2
import nltk
import fnmatch
import math
from nltk.corpus import stopwords
from nltk.stem.lancaster import LancasterStemmer

def annotate(in_f,out_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter='\t')
		#length = str(len(reader))
        	counter = 1
		#list_out = list()
		f = open(out_f,'w')
		for row in reader:
			list_out = list()
			print 'Finished: ' + str(counter)
			counter += 1
			#print str(counter) + ' of ' + length + ' processed'
			method_a = row[0]
			method_b = row[1]
			api = row[2]
			list_out += create_features(method_a, method_b, api)
			#list_out += create_features(method_a, method_b, api)
			for el in list_out:
				f.write(el)
		f.close()

def get_first_line(in_file):
	lines_1 = open(in_file,'r').readlines()
	counter = 0
	for line in lines_1:
		if '/**' in line:
			return lines_1[counter+1]
		counter += 1
	return ''

def create_features(method_a, method_b, api):
	iq_text = get_first_line(method_a)
	#f = open(method_a,'r')
	#iq_text = f.read().lower()
	#f.close()
	#iq_nouns,iq_verbs = filter_pos(iq_text)
	
	matches = []
        for root, dirnames, filenames in os.walk(api):
                for filename in fnmatch.filter(filenames, "*"):
                        matches.append(os.path.join(root, filename))			
	features = dict()
	list_out = list()	
	for match in matches:
		features[match] = calc_feature_vector(match,iq_text)
		#list_out.append(method_a + '\t' + match+'\t'+str(features[match][0]) +'\t'+str(features[match][1]) + '\t'+'0' + '\n')
		list_out.append(method_a + '\t' + match+'\t'+str(features[match][0]) +'\t'+str(features[match][1]) + '\t'+str(features[match][2]) + '\t'+str(features[match][3]) + '\t'+'0' + '\n')
	# now do the correct one
 	features[method_b] = calc_feature_vector(method_b,iq_text)
	#list_out.append(method_a + '\t' + method_b+'\t'+str(features[method_b][0]) +'\t'+str(features[match][1]) + '\t'+'1' + '\n')
	list_out.append(method_a + '\t' + method_b+'\t'+str(features[method_b][0]) +'\t'+str(features[method_b][1]) + '\t'+str(features[method_b][2]) + '\t'+str(features[method_b][3]) + '\t'+'1' + '\n')
	return list_out
	
def calc_feature_vector(match,iq_text):
	feature_vector = list()
	#f = open(match)
	#text = f.read().lower()
	#f.close()
	text = get_first_line(match)
	#iq_nouns,iq_verbs = filter_pos(iq_text)
	feature_vector.append(overlapping_text(text,iq_text))
	feature_vector.append(diff_length(iq_text,text))
	#m_nouns, m_verbs = filter_pos(text)
	feature_vector.append(.5)
        feature_vector.append(.5)
	#feature_vector.append(jaccard_dist((m_nouns),(iq_nouns)))
	#feature_vector.append(jaccard_dist((m_verbs),(iq_verbs)))
	return feature_vector

def overlapping_text(text_1, text_2):
	st = LancasterStemmer()
	cachedStopWords = get_stopwords()
	text_1_list = ([st.stem(word) for word in text_1.split() if word not in cachedStopWords])
	text_2_list = ([st.stem(word) for word in text_2.split() if word not in cachedStopWords])
	return jaccard_dist(text_1_list, text_2_list)
	'''
	st = LancasterStemmer()
	#cachedStopWords = get_stopwords()
	#cachedStopWords.append('/**')	
	#cachedStopWords.append('*/')
	#cachedStopWords.append('*')
	text_1_list = ([st.stem(word) for word in text_1.split() if word not in most_freq])
	text_2_list = ([st.stem(word) for word in text_2.split() if word not in most_freq])
	#st.stem(word)
	#print text_1_list
	return jaccard_dist(text_1_list, text_2_list)
	'''
def get_stopwords():
	
	cachedStopWords = stopwords.words("english")
	'''
	cachedStopWords.append('/**')
	cachedStopWords.append('*/')
	cachedStopWords.append('*')
	cachedStopWords.append('')
	cachedStopWords.append('/\n')
	cachedStopWords.append('*//\n')
	cachedStopWords.append('*/\n')
	cachedStopWords.append('/**/\n')
	cachedStopWords.append('software')
	cachedStopWords.append('license')
	cachedStopWords.append('under')
	cachedStopWords.append('and')
	cachedStopWords.append('you')
	cachedStopWords.append('@param')
	cachedStopWords.append('{@link')
	cachedStopWords.append('be')
	cachedStopWords.append('on')
	cachedStopWords.append('distributed')
	cachedStopWords.append('apache')
	cachedStopWords.append('with')
	cachedStopWords.append('may')
	cachedStopWords.append('{@code')
	cachedStopWords.append('see')
	cachedStopWords.append('@since')
	cachedStopWords.append('copyright')
	cachedStopWords.append('without')
	cachedStopWords.append('use')
	cachedStopWords.append('return')
	'''
	return cachedStopWords

def diff_length(text_1,text_2):
	spaces_1 = text_1.split(' ')
	spaces_2 = text_2.split(' ')
	len_1 = len(spaces_1)
	len_2 = len(spaces_2)
	return float(abs(len_1 - len_2))/float(min(len_2,len_1))
	
def jaccard_dist(a,b):
	a = set(a)
	b = set(b)
	union = a.union(b)
	inter = a.intersection(b)
	len_union = float(len(union))
	len_inter = float(len(inter))
	try:
		jsim = len_inter/(len_union-len_inter)
	except ZeroDivisionError:
		jsim = 0
	jdist = 1-jsim
	return jdist
	#return len(inter)
		
def filter_pos(text):
	st = LancasterStemmer()
	tokens = nltk.word_tokenize(text)
	tagged = nltk.pos_tag(tokens)
	nouns = list()
	verbs = list()
	for (word, tag) in tagged:
		if tag.startswith('N'):
            		nouns.append(st.stem(word))
		elif tag.startswith('V'):
			verbs.append(st.stem(word))
	return nouns,verbs


if __name__ == '__main__':
        #api = sys.argv[1] 
	#initial_query = sys.argv[2]
	in_f = sys.argv[1]
	out_f = sys.argv[2]
	annotate(in_f,out_f)


