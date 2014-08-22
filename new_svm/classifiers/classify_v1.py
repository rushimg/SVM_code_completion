'''
Classify Webpages as being articles relating to adulteration incident or not. 

Features are Words added to query("","adulteration","fraud") and pagerank(1,2,3) crossed

Add in url stems of links, text data and is_pdf as features
'''
from bs4 import BeautifulSoup
from sklearn.svm import LinearSVC
import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn import metrics
from operator import itemgetter
from sklearn.metrics import classification_report
import csv,sys,os
import urllib2
from sklearn import cross_validation
from sklearn import svm

def run_svm(in_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter='\t')
		training_data = []
		training_labels = []
		tot_wrong = 0
		avg_wrong = 0
		for row in reader:
			data = list()
			data.append(float(row[2]))
			#data.append(float(row[3]))
			#data.append(float(row[4]))
			#data.append(float(row[5]))
			training_data.append(data)
			training_labels.append(int(row[6]))
			if int(row[6]) == 1:
				print row[0] + ' : ' + str(row[5])
			else:
				tot_wrong += float(row[5])
				avg_wrong += 1
	print "Average for 0 label = " + str(tot_wrong/float(avg_wrong))
						
	text_data = training_data
	text_labels = training_labels

	X = np.array([el for el in text_data])
	Y = np.array([el for el in text_labels])

	model = LinearSVC()
        scores = cross_validation.cross_val_score(model, X, Y, cv=4, scoring='f1')
        print scores
	print("F1 Score: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))
	
if __name__ == '__main__':
        run_svm(sys.argv[1])


