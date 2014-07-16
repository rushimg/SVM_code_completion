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

def run_svm(in_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter='\t')
		training_data = []
		training_labels = []
		total_count = 0
		pos_count =0
		for row in reader:
			data = list()
			#get_html_text(row[1])
			data.append(float(row[1]))
			data.append(float(row[2]))
			data.append(float(row[3]))
			training_data.append(data)
			training_labels.append(int(row[4]))
	
	text_data = training_data
	text_labels = training_labels
	trainset_size = int(round(len(text_data)*0.75)) # i chose this threshold arbitrarily...to discuss
	print 'The training set size for this classifier is ' + str(trainset_size) + '\n'

	X_train = np.array([el for el in text_data[0:trainset_size]])
	y_train = np.array([el for el in text_labels[0:trainset_size]])

	X_test = np.array([el for el in text_data[trainset_size+1:len(text_data)]]) 
	y_test = np.array([el for el in text_labels[trainset_size+1:len(text_labels)]]) 

	#vectorizer = CountVectorizer(binary=True)
	#vectorizer = TfidfVectorizer(min_df=2, 
	#ngram_range=(1, 2), 
	#stop_words='english', 
	#strip_accents='unicode', 
	#norm='l2')
	#test_string = unicode(text_data[0])
	#print "Example string: " + test_string
	#print "Preprocessed string: " + vectorizer.build_preprocessor()(test_string)
	#print "Tokenized string:" + str(vectorizer.build_tokenizer()(test_string))
	#print "N-gram data string:" + str(vectorizer.build_analyzer()(test_string))
	#print "\n"
	#X_train = vectorizer.fit_transform(X_train)
	#X_test = vectorizer.transform(X_test)
	#print X_train
	
	svm_classifier = LinearSVC().fit(X_train, y_train)
	y_svm_predicted = svm_classifier.predict(X_test)
	#y_svm_predicted = svm_classifier.predict(np.array([2,2,118]))
	#../code_corpus/HtmlCleaner/comments/TagNode.java_14     4       4       125     1
	print y_svm_predicted

	print "MODEL: Linear SVC\n"

	print 'The precision for this classifier is ' + str(metrics.precision_score(y_test, y_svm_predicted))
	print 'The recall for this classifier is ' + str(metrics.recall_score(y_test, y_svm_predicted))
	print 'The f1 for this classifier is ' + str(metrics.f1_score(y_test, y_svm_predicted))
	print 'The accuracy for this classifier is ' + str(metrics.accuracy_score(y_test, y_svm_predicted))

	print '\nHere is the classification report:'
	print classification_report(y_test, y_svm_predicted)

	#simple thing to do would be to up the n-grams to bigrams; try varying ngram_range from (1, 1) to (1, 2)
	#we could also modify the vectorizer to stem or lemmatize
	#print '\nHere is the confusion matrix:'
	#print metrics.confusion_matrix(y_test, y_svm_predicted, labels=unique(data_labels))

if __name__ == '__main__':
        run_svm(sys.argv[1])


