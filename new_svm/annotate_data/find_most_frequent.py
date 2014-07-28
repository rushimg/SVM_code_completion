'''
FInd most common words in the APIs, we would like to treat these words as Noise/Equivalent of normal english stopwords
'''
from operator import itemgetter
from sklearn.metrics import classification_report
import csv,sys,os
import urllib2
import nltk
import fnmatch
import nltk
from collections import Counter


APIS = ['../../code_corpus/google-http-client/comments/','../../code_corpus/httpclient/comments/','../../code_corpus/jgrapht/comments/','../../code_corpus/jung_api/comments/','../../code_corpus/HtmlCleaner/comments/']

def cal_most_freq():
	text = ''
	for api in APIS:
		text += (' ' +get_all_words(api))
	#fd = nltk.FreqDist(word for word in text.split(' '))
	#print fd.most_common()
	c = Counter(text.split(' '))
	temp_list = list()
	for (el,num) in c.most_common()[:100]:
		temp_list.append(el)
	print temp_list
	#print c.most_common()[:100]	

def get_all_words(api):
	print 'Processing: ' + api
	matches = []
        for root, dirnames, filenames in os.walk(api):
                for filename in fnmatch.filter(filenames, "*"):
                        matches.append(os.path.join(root, filename))			
	all_text = ''
	for match in matches:
		f = open(match,'rb')
		#print f.read().lower()
		all_text += (' ' + f.read().lower())

	return all_text
	
if __name__ == '__main__':
	cal_most_freq()


