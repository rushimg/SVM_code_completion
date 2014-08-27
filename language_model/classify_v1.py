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
		counter = 0
		for row in reader:
			#manually filtering:
			
			#counter += 1
			#if (int(row[1]) == 1) or ((counter % 100) == 0):
			data = list()
			data.append(float(row[0]))
			#data.append(float(row[1]))
			data.append(float(row[2]))
			#data.append(float(row[3]))
			data.append(float(row[4]))
			training_data.append(data)
			training_labels.append(int(row[5]))
			if int(row[5]) == 1:
				counter += 1
	print counter 		
	text_data = training_data
	text_labels = training_labels

	X = np.array([el for el in text_data])
	Y = np.array([el for el in text_labels])

	model = LinearSVC()
        #model = svm.SVC()
	scores = cross_validation.cross_val_score(model, X, Y, cv=4, scoring='f1')
        print scores
	print("F1 Score: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))
	
if __name__ == '__main__':
        run_svm(sys.argv[1])


