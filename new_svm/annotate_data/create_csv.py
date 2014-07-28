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
	
			#f = open(out_f,'w')
			for el in list_out:
				f.write(el)
		f.close()
#def create_features(api, initial_queryi,out_f):
def create_features(method_a, method_b, api):
	f = open(method_a,'r')
	iq_text = f.read()
	f.close()
	iq_nouns,iq_verbs = filter_pos(iq_text)
	
	matches = []
        for root, dirnames, filenames in os.walk(api):
                for filename in fnmatch.filter(filenames, "*"):
                        matches.append(os.path.join(root, filename))			
	features = dict()
	list_out = list()	
	#length = str(len(matches))
        #counter = 1
	for match in matches:
		#print str(counter) + ' of ' + length + ' processed'
		#if counter < 20:
			#print match
		#counter += 1
		features[match] = calc_feature_vector(match,iq_nouns,iq_verbs,iq_text)
		list_out.append(method_a + '\t' + match+'\t'+str(features[match][0]) + '\t'+ str(features[match][1]) + '\t' + str(features[match][2])+'\t'+'0' + '\n')
	
	# now do the correct one
 	features[method_b] = calc_feature_vector(method_b,iq_nouns,iq_verbs,iq_text)
	list_out.append(method_a + '\t' + method_b+'\t'+str(features[method_b][0]) + '\t'+ str(features[method_b][1]) + '\t' + str(features[method_b][2])+'\t'+'1' + '\n')
	
	return list_out
	
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
        #api = sys.argv[1] 
	#initial_query = sys.argv[2]
	in_f = sys.argv[1]
	out_f = sys.argv[2]
	annotate(in_f,out_f)


