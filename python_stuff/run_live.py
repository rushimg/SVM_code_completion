import subprocess
import os, sys

CORPUS = '../code_corpus/'
NUM_RETURN_RESULTS = 10


def pre_proc_code():
	# grab all corpus files
	# pre-write the tfidfs to file so can be read form file

def initial_query(in_file):
	f_in = open(in_file, 'r')
	in_text = f.read()	
	# return a set of documents to be used as testing data

def relevance_feed_back(test_docs):
	# user manually annotates about ten examples which will be used as the training set	
	# test set is entire corpus

def run_svm(in_dir):	
	print "Running SVM on " + in_dir
	
	clean_cmd = 'bash ../train_test/clean_tests.sh ' + in_dir
	os.system(clean_cmd)
	
	train_cmd = 'python code_2_vecs_tfidf_cmd.py ' + in_dir
	os.system(train_cmd)
	
	test_cmd = 'python tcode_2_vecs_tfidf_cmd.py ' + in_dir
	os.system(test_cmd)
	
	learn_cmd = '../svm_light/svm_learn ' + in_dir+'train.dat ' + in_dir+'model'	
	os.system(learn_cmd)
	
	classify_cmd = '../svm_light/svm_classify ' + in_dir+'test.dat ' + in_dir+'model ' + in_dir+'predictions '
        os.system(classify_cmd)

 	map_cmd = 'bash ../train_test/map_tests_pred.sh ' + in_dir
	os.system(map_cmd)

def return_results():
	#return top k results

	
if __name__=='__main__':
        inital_query(sys.argv[1])
