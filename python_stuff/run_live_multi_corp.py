import subprocess
import os, sys
import fnmatch
from tfidf import tfidf
import csv
import math
import ast

#CORPUS = '../code_corpus/regular/facebook/'
NUM_RETURN_RESULTS = 10
CORPUS_1 = '../code_corpus/regular/facebook/'
CORPUS_2 = '../code_corpus/regular/twitter/'
#TMP_DIR = '../tt_live/demo_1/'

def pre_proc_code(run_pre_proc):
	# grab all corpus files
	# pre-write the tfidfs to file so can be read form file

	if not run_pre_proc:

		matches = []
		words_per_file = dict()
		for root, dirnames, filenames in os.walk(CORPUS_1):
         	       for filename in fnmatch.filter(filenames, "*.java_*"):
                	        matches.append(os.path.join(root, filename))
		for root, dirnames, filenames in os.walk(CORPUS_2):
                       for filename in fnmatch.filter(filenames, "*.java_*"):
                                matches.append(os.path.join(root, filename))

		words = set()
		for match in matches:
			print match
			words_per_file[match] = get_all_words(match)
			words = words.union(words_per_file[match])			
		'''	
		print "Writing words per file to file tmp/words_per_file.csv'"
                f_words_pf = open(TMP_DIR+'words_per_file_tmp.csv', 'w')
                for w in words_per_file:
                        f_words_pf.write(w + ','  + str(words_per_file[w]) + '\n')
                f_words_pf.close()
                print "Writing all words to file tmp/words_tmp.csv'"
                f_words = open(TMP_DIR+'words_tmp.csv', 'w')
                for word in words:
                        f_words.write(word + '\n')                    
                f_words.close()
		'''
		print "calculuating idfs"
		calc_freq = tfidf(words, words_per_file)
		#print "actually calculating idfs"
		idfs = calc_freq.getAllIdfs()	
		
		print "Writing idf values to file tmp/idfs_tmp.csv'"
		f_idfs = open(TMP_DIR+'idfs_tmp.csv', 'w')
		for key in idfs:
			if key != '':
				f_idfs.write(key + ',' + str(idfs[key]) + '\n')			
		f_idfs.close()
		
		#caluclate feature vectors
		feature_vectors_dict = dict()
		f_feature_vecs = open(TMP_DIR+'/feature_vecs_tmp.csv','w')
		for match in matches:
			wrd_cnts = str(get_word_counts(words_per_file[match], words, calc_freq))
                	f_feature_vecs.write(match+','+ wrd_cnts +'\n')
			feature_vectors_dict[match] = wrd_cnts   
		
	else:
		print "Reading values from files in tmp/ directory"
		words = set()
		'''
		f_words = open(TMP_DIR+'words_tmp.csv', 'r')
        0%        for word in f_words.readlines():
                	words.add(word.replace('\n',''))        
		f_words.close()
		'''
		idfs = dict()
                f_idfs = open(TMP_DIR+'idfs_tmp.csv', 'r')
                for line in f_idfs.readlines():
			comma = line.split(',')
			idfs[comma[0]] = float((comma[1]).replace('\n',''))
                        #print comma[0] + ',' + comma[1].replace('\n','')
			#f_idfs.write(key + ',' + str(idfs[key]) + '\n')
                f_idfs.close()
	              
                feature_vectors_dict = dict()
                with open(TMP_DIR+'feature_vecs_tmp.csv', 'r') as csvfile:
                        reader = csv.reader(csvfile)
                        for row in reader:
                                feature_vectors_dict[row[0]] = (row[1]).replace('\n','')
                        	#print (row[0] + ','  + row[1])
                csvfile.close()
        

		'''
		words_per_file = dict()
		with open(TMP_DIR+'words_per_file_tmp.csv', 'r') as csvfile:
                	reader = csv.reader(csvfile)
			for row in spamreader:
				words_per_file[row[0]] = eval(row[1])
                        #f_words_pf.write(w + ','  + str(words_per_file[w]) + '\n')
                csvfile.close()
		'''
		# read featute vectors from file
	return feature_vectors_dict, idfs

def get_word_counts(file_words, all_words, calc_freq):
        word_count_dict = dict()
        # initialize word dictionary
        word_2_num_trans = list()
        for word in all_words:
                word_count_dict[word.lower()] = 0
        for w in file_words:
                w = w.lower()
                #if w == 'double':
                        #print "here"
                        #print calc_freq.calc_tfidf(w,file_words)
                if w in all_words:
                        word_count_dict[w.lower()] = calc_freq.calc_tfidf(w,file_words)
        return clean_output_4_svm(word_count_dict)

#make the output of form feature:value, no zeo valued features
def clean_output_4_svm(word_count_dict):
        length = len(word_count_dict)
        vals = word_count_dict.values()
        keys =  word_count_dict.keys()
        return_str = ""
        for i in range(0,length):
                if vals[i] != 0:
                        # no features with index < 1
                        #return_str += (' ' +  keys[i] + ":" + str(vals[i]))
                        return_str += (' ' + str(i+1) + ":" + str(vals[i]))
        return return_str

# get a set of all words in all docs
def get_all_words(file_name):
        words_in_file = list()
        f = open(file_name, 'r')
        lines = f.readlines()
        for line in lines:
                spaces = (remove_wierd_chars(line)).split(' ')
                #spaces = line.split(' ')
		for space in spaces:
                        words_in_file.append(space)
        return words_in_file

def remove_wierd_chars(string):
        string = string.replace('{',' ')
        string = string.replace('}',' ')
        string = string.replace(')',' ')
        string = string.replace('(',' ')
        string = string.replace('.',' ')
        string = string.replace('!',' ')
        string = string.replace(';',' ')
        string = string.replace('"',' ')
        string = string.replace('\n',' ')
        string = string.replace('\r',' ')
        string = string.replace('\t',' ')
        string = string.replace(',',' ')
        string = string.replace('  ',' ')
        string = string.replace('  ',' ')
	string = string.replace(':','')
	#string = string.replace('*/',' ')
	#string = string.replace('*','')
	#string = string.replace('/*',' ')
	#string = string.replace('  ',' ')
        #string = string.replace('  ',' ')
        return string.lower()

def initial_query(in_file,idfs):
	#f_in = open(in_file, 'r')
	#in_text = f_in.read()	
	#TODO: problem here with creating the feature vector for the input 
		
	print "Calculating feature vectors for input file: " + in_file
	in_words = get_all_words(in_file)
	#calc_freq = tfidf(idfs.keys(), in_words, idfs)
	#print idfs.keys()[85]
	#print idfs.keys()[83]
	#print idfs.keys()[82]
	#print idfs.keys()[84]
	
	#print in_words
	
	f_idfs = open(TMP_DIR+'idfs_tmp.csv','r')
	wrd_cnts = ''
	word_counter = 1
	for line in f_idfs.readlines():
		comma = line.split(',')
		word_counter += 1
		if comma[0] in in_words:
			tf = term_freq(comma[0], in_words)
			#print word_counter
			wrd_cnts = wrd_cnts + ' ' + str(word_counter) + ':' + str(float(comma[1].replace('\n',''))*tf)
	
	#calc_freq= tfidf(idfs.keys(), None, idfs)
	#wrd_cnts = str(get_word_counts(in_words, idfs.keys(), calc_freq))
	#print "1 "
	#print wrd_cnts
	return wrd_cnts

def term_freq(term,document):
	tf_count = 0
	length_doc = len(document)
	for word in document:
        	if word == term:
			tf_count += 1
		tf = float(tf_count)/float(length_doc)
	return tf
	
def retrieve_initial_set(input_feature_vec, feature_vecs):
	# return a set of documents to be used as testing data
	input_dict = convert_to_dict(input_feature_vec)
	ranking_dict = dict()
	for key in feature_vecs:
		ranking_dict[key] = cosine_sim(convert_to_dict(feature_vecs[key]),input_dict)
		#if key == "../code_corpus/regular/facebook/DefaultFacebookClient.java_22":
		#	print sorted(convert_to_dict(feature_vecs[key]).keys())
		#	print sorted(input_dict.keys())
		#	print  ranking_dict[key]	
	
	sorted_values = sorted([(value,key) for (key,value) in ranking_dict.items()])
	length = len(sorted_values)
	anticipated_negative = sorted_values[0:3]
	anticipated_positive = list(reversed(sorted_values[(length-NUM_RETURN_RESULTS):length]))
	return_set = anticipated_positive + anticipated_negative	
	#return reversed(sorted_values[(length-NUM_RETURN_RESULTS):length])
	return return_set

def convert_to_dict(feature_vec):
	if feature_vec[0] == ' ':
                feature_vec = feature_vec[1:]
        eval_string= '{' + feature_vec.replace(' ',',') + '}'
        temp_dict = ast.literal_eval(eval_string)
        return temp_dict

def cosine_sim(vec_a, vec_b):
	#print vec_a
	# these are sparse vecors in string form with zero valued features removed
	# This function cannot be used when vectors are in the normal form
	big_a = 0
	big_b = 0
	for b in vec_b:
		big_b += float(vec_b[b])*float(vec_b[b])
	card_a = len(vec_a)
	card_b = len(vec_b)
	a_dot_b = 0
	for a in vec_a:
		big_a += float(vec_a[a])*float(vec_a[a])
		if a in vec_b:
			a_dot_b += vec_a[a] * vec_b[a]
	#print a_dot_b
	return (float(a_dot_b)/(math.sqrt(float(big_a))*math.sqrt(float(big_b))))

def relevance_feed_back(train_docs,feature_vectors_dict):
	# user manually annotates about ten examples which will be used as the training set	
	# test set is entire corpus
	counter = 0
	train_values = dict()
	for doc in train_docs:
		temp_file = open(doc[1])
		temp_text = temp_file.read()
		print "Training Example " + str(counter) + ' :'
		print doc[1]
		print temp_text
		temp_input_var = str(input("Enter Classification( 1 or -1): "))
		if (temp_input_var != '-1') and  (temp_input_var != '1'):
			#add check here
			temp_input_var = '-1'
		train_values[doc[1]] = temp_input_var 
		temp_file.close()
		counter += 1
	#print train_values

	f_train_corr =  open(TMP_DIR+'train_corr','w')
	f_train_wrong = open(TMP_DIR+'train_wrong','w')
	f_train_dat = open(TMP_DIR+'train.dat','w')
	for key in train_values:
		if train_values[key] == '1':
			f_train_corr.write(key + '\n')
			f_train_dat.write('+1' + feature_vectors_dict[key] +'\n')
			#print key
		elif train_values[key] == '-1':
			f_train_wrong.write(key + '\n')
			f_train_dat.write('-1' + feature_vectors_dict[key] +'\n')
	f_train_corr.close()	
	f_train_wrong.close()
	f_train_dat.close()

	print "Training data files Created"

def test_data(feature_vectors_dict):
	'''* test on the entire corpus including the training(?)'''
	'''** this does not need to be created every time'''
	f_test = open(TMP_DIR+'test.dat','w')
	f_test_map = open(TMP_DIR+'test_map.dat','w')
	for k in feature_vectors_dict:
		f_test.write('0' + feature_vectors_dict[k] + '\n')
		f_test_map.write(k + '\n')
	f_test.close()
	f_test_map.close()
	print "Testing data files Created"
	
def run_svm():	
	print "Running SVM"
	
	#clean_cmd = 'bash ../train_test/clean_tests.sh ' + in_dir
	#os.system(clean_cmd)
	
	#train_cmd = 'python code_2_vecs_tfidf_cmd.py ' + in_dir
	#os.system(train_cmd)
	
	#test_cmd = 'python tcode_2_vecs_tfidf_cmd.py ' + in_dir
	#os.system(test_cmd)
	
	learn_cmd = '../svm_light/svm_learn ' + TMP_DIR+'train.dat ' + TMP_DIR+'model'	
	os.system(learn_cmd)
	
	classify_cmd = '../svm_light/svm_classify ' + TMP_DIR+'test.dat ' + TMP_DIR+'model ' + TMP_DIR+'predictions '
        os.system(classify_cmd)

 	#map_cmd = 'bash ../train_test/map_tests_pred.sh ' + in_dir
	#os.system(map_cmd)

	print "Done running SVM Classfier"

def return_results():
	f_map = open(TMP_DIR+'test_map.dat','r')
	f_predictions =  open(TMP_DIR+'predictions','r')
	results_dict = dict()
	map_lines = f_map.readlines()
	counter = 0
	for line in f_predictions.readlines():
		results_dict[map_lines[counter]] = float(line.replace('\n',''))
		counter +=1

	sorted_values = sorted([(value,key) for (key,value) in results_dict.items()])
	#sorted_values = reversed(sorted_values)
	length = len(sorted_values)
        for val in reversed(sorted_values[(length-NUM_RETURN_RESULTS):length]):
		print "File : " + val[1].replace('\n','')
		print "Predicted Value: " + str(val[0]) + '\n'
		temp_file = open(val[1].replace('\n',''), 'r')
		#temp_file_code = open((val[1]+'_code').replace('\n',''), 'r')
		#print val[1].replace('\n','')
		print temp_file.read()
		#print temp_file_code.read()
		
if __name__=='__main__':

	run_process = True
	if (sys.argv[3] == 'F'):
		run_process = False	
	
	global TMP_DIR
	in_file = sys.argv[1]
	dash_array =  in_file.split('/')
	tmp_dir = ''
	for elem in dash_array[:-1]:
		tmp_dir += elem +'/'
	global TMP_DIR
	TMP_DIR = tmp_dir
        #print TMP_DIR

	global CORPUS 
	CORPUS = sys.argv[2]

	feature_vectors_dict, idfs = pre_proc_code(run_process)
	input_feature_vec = initial_query(sys.argv[1], idfs)
	train_set = retrieve_initial_set(input_feature_vec, feature_vectors_dict)
	relevance_feed_back(train_set,feature_vectors_dict)
	test_data(feature_vectors_dict)
	run_svm()
	return_results()
