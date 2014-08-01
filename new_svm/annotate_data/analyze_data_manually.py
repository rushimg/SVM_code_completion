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
from nltk.corpus import stopwords
from nltk.stem.lancaster import LancasterStemmer

def annotate(in_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter='\t')
        	global_counter = 1	
		for row in reader:
			print "Pair: " + str(global_counter)
			
			global_counter += 1
			method_a = row[0]
			method_b = row[1]

			#print open(method_a,'r').read()
			print method_a	
			lines_1 = open(method_a,'r').readlines() 	
			counter = 0
			for line in lines_1:
				if '/**' in line:
					print lines_1[counter+1]
				counter += 1
			
			lines_2 = open(method_b,'r').readlines()
			counter = 0
			for line in lines_2:
				if '/**' in line:
					print lines_2[counter+1]
				counter += 1
			
			#print open(method_b,'r').read()

if __name__ == '__main__':
	in_f = sys.argv[1]
	annotate(in_f)
