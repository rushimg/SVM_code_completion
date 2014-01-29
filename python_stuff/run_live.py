import subprocess
import os, sys
import fnmatch
from tfidf import tfidf
import csv

CORPUS = '../code_corpus/regular/facebook/'
NUM_RETURN_RESULTS = 10
TMP_DIR = 'tmp/'

def pre_proc_code(run_pre_proc):
	# grab all corpus files
	# pre-write the tfidfs to file so can be read form file

	if not run_pre_proc:

		matches = []
		words_per_file = dict()
		for root, dirnames, filenames in os.walk(CORPUS):
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

        return string.lower()

def initial_query(in_file,idfs):
	#f_in = open(in_file, 'r')
	#in_text = f_in.read()	
	print "Calculating feature vectors for input file: " + in_file
	in_words = get_all_words(in_file)
	calc_freq= tfidf(idfs.keys, None, idfs)
	wrd_cnts = str(get_word_counts(in_words, idfs.keys(), calc_freq))
	return wrd_cnts
	
def retrieve_initial_set(input_feature_vec, feature_vecs):
	# return a set of documents to be used as testing data
	return "NOT_DONE_YET"

def cosine_sim(vec_a, vec_b):
	

	# calculate the cosine similarity between two sparse vectors	
	return "NOT DONE YET"

def relevance_feed_back(test_docs):
	# user manually annotates about ten examples which will be used as the training set	
	# test set is entire corpus
	y = 2

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
	k = 5
	
if __name__=='__main__':
	feature_vectors_dict, idfs = pre_proc_code(True)
	input_feature_vec = initial_query(sys.argv[1], idfs)
	retrieve_initial_set(input_feature_vec, feature_vecstors_dict)
