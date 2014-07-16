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

def create_features(api, initial_queryi,out_f):
	f = open(initial_query,'r')
	iq_text = f.read()
	f.close()
	iq_nouns,iq_verbs = filter_pos(iq_text)
	matches = []
        for root, dirnames, filenames in os.walk(api):
                for filename in fnmatch.filter(filenames, "*"):
                        matches.append(os.path.join(root, filename))			
	features = dict()	
	f_out = open(out_f,'w')
	length = str(len(matches))
	counter = 0
	for match in matches:
		print str(counter) + ' of ' + length + ' processed' 
		counter += 1
		features[match] = calc_feature_vector(match,iq_nouns,iq_verbs,iq_text)
		f_out.write(match+'\t'+str(features[match][0]) + '\t'+ str(features[match][1]) + '\t' + str(features[match][2])+'\t'+'0' + '\n')
	f_out.close()
	print "Results written to: " + out_f

def calc_feature_vector(match,iq_nouns,iq_verbs,iq_text):
	feature_vector = list()
	f = open(match)
	text = f.read()
	f.close()
	m_nouns, m_verbs = filter_pos(text)
	feature_vector.append(jaccard_dist((m_nouns),(iq_nouns)))
	feature_vector.append(jaccard_dist((m_verbs),(iq_verbs)))
	feature_vector.append(diff_length(iq_text,text))
	return feature_vector

def diff_length(text_1,text_2):
	spaces_1 = text_1.split(' ')
	spaces_2 = text_2.split(' ')
	return abs(len(spaces_1) - len(spaces_2))
	
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
		jsim = len_inter
	jdist = 1-jsim
	return jdist
	#return len(inter)
		
def filter_pos(text):
	tokens = nltk.word_tokenize(text)
	tagged = nltk.pos_tag(tokens)
	nouns = list()
	verbs = list()
	for (word, tag) in tagged:
		if tag.startswith('N'):
            		nouns.append(word)
		elif tag.startswith('V'):
			verbs.append(word)
	return nouns,verbs


if __name__ == '__main__':
        api = sys.argv[1] 
	initial_query = sys.argv[2]
	out_f = sys.argv[3]
	create_features(api, initial_query,out_f)


