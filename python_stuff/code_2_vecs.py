import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*java*"
DIR_NAME = "../code_corpus/train/aws_train_code"
TRAIN = "../train_test/train.dat"
WORDS = "../train_test/words"
CORRECT = ['XpathUtils.java_9']
def process_files():
        words_per_file = dict()
	matches = []
        for root, dirnames, filenames in os.walk(DIR_NAME):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))
	words = set()
	length = str(len(matches))
	count = 0
	# first iteration through matches to get all words
	for match in matches:
		count +=1
		print str(count) + " of " + length + " training example preprocessing done"
		words_per_file[match] = get_all_words(match)
		words = words.union(words_per_file[match])
	print "prelinary processing done"
	train_f = open(TRAIN, 'w')
	# second iteration through matches to get all word counts
	pattern = MATCH.replace('*','')
	count = 0
	for match in matches:
		count +=1
		print str(count) + " of " + length + " training examples done"
		#print match.replace(DIR_NAME+'/code_','')
		#print CORRECT
		if match.replace(DIR_NAME+'/'+pattern,'') in CORRECT:
			#print words_per_file[match]
			train_f.write('1')
		else:
			train_f.write('-1')
		train_f.write(str(get_word_counts(words_per_file[match], words)))
		train_f.write('\n')
	train_f.close()	
	print "Results printed to file: " + str(TRAIN)
	
# this is by far not the best way to do this
def get_word_counts(file_words, all_words):
	word_count_dict = dict()
	# initialize word dictionary
	word_2_num_trans = list()
	words_f = open(WORDS, 'w')
	for word in all_words:	
		word_count_dict[word.lower()] = 0
		words_f.write(word.lower()+'\n')
	words_f.close()
	for w in file_words:
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
def get_all_words(file_name):
	words_in_file = set()
	f = open(file_name, 'r')
	lines = f.readlines()
	for line in lines:
		spaces = (remove_wierd_chars(line)).split(' ')
		for space in spaces:
			words_in_file.add(space)
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


