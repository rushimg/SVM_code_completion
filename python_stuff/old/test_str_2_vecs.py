import sys,os,re
import fnmatch

TEST_IN = "../code_corpus/test/test_strings"
TEST = "../train_test/test.dat"
WORDS = "../train_test/words"
CORRECT = ['5','6']

def process_files():
        words_per_file = dict()
	
	test_strings = open(TEST_IN, 'r')
	words = get_all_words_file()

	
	test_f = open(TEST, 'w')
	for l in test_strings.readlines():
		if True:
			test_f.write('1')
		else:
			test_f.write('-1')
		test_f.write(str(get_word_counts(l,words)))
		test_f.write('\n')
	test_f.close()	
	print "Results printed to file: " + str(TEST)
	
# this is by far not the best way to do this
def get_word_counts(file_words, all_words):
	word_count_dict = dict()
	# initialize word dictionary
	word_2_num_trans = list()
	words_f = open(WORDS, 'r')
	all_words = words_f.readlines()
	#print all_words
	for word in all_words:	
		word_count_dict[word.lower()] = 0
		#words_f.write(word.lower()+'\n')
	words_f.close()
	for w in file_words.split(' '):
		#w = remove_wierd_chars(w)
		if w in all_words:
			word_count_dict[w.lower()] += 1  	
	return clean_output_4_svm(word_count_dict)
	
#make the output of form feature:value, no zeo valued features
def clean_output_4_svm(word_count_dict):
	length = len(word_count_dict)
	vals = word_count_dict.values()
	return_str = ""
	for i in range(0,length):
		if vals[i] != 0:
			# no features with index < 1
			return_str += (' ' + str(i+1) + ":" + str(vals[i])) 		
	return return_str

# get a set of all words in all docs
def get_all_words_file():
	words_in_file = set()
	f = open(WORDS, 'r')
	lines = f.readlines()
	for line in lines:
		words_in_file.add(line)
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
	string = string.replace('\t',' ')
	string = string.replace(',',' ')
	string = string.replace('  ',' ')
	string = string.replace('  ',' ')
	return string.lower()

if __name__=='__main__':
	process_files()


