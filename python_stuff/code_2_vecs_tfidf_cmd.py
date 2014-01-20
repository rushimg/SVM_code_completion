import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET
from tfidf import tfidf

DIR_NAME = "../code_corpus/aws"
#TRAIN = "../train_test/train_tf_2.dat"
#WORDS = "../train_test/words_tf_2"

def process_files(in_dir):
	
	global WORDS, TRAIN
	
	WORDS = in_dir+'words'
	TRAIN = in_dir+'train.dat'
	
	CORRECT_LIST = []
	matches = []
	#CORRECT_LIST = (open(in_dir+'train_corr','r')).readlines()
	for line in (open(in_dir+'train_corr','r')).readlines():
		if line.replace('\n','') != '':
			CORRECT_LIST.append(line.replace('\n',''))
			matches.append(line.replace('\n',''))
	#print CORRECT_LIST
	for line in open(in_dir+'train_wrong','r').readlines():
                if line.replace('\n','') != '':
			matches.append(line.replace('\n',''))
	#print matches
	#matches = (open(in_dir+'train_corr','r')).readlines() + (open(in_dir+'train_wrong','r')).readlines()
	words_per_file = dict()
	
	words = set()
	length = str(len(matches))
	count = 0
	user_counter = 0 
	# first iteration through matches to get all words
	for match in matches:
		count +=1
		print str(count) + " of " + length + " training example preprocessing done"
		#if 'tweet' in get_all_words(match) :
		#	user_counter += 1
		#	print user_counter
		words_per_file[match] = get_all_words(match)
		words = words.union(words_per_file[match])
	
	print "preliminary processing done"
	train_f = open(TRAIN, 'w')

	# second iteration through matches to get all word counts
	count = 0
	calc_freq = tfidf(words, words_per_file)
	print "Done calculating idfs"	
	#print "PARAM " + str(calc_freq.getIDF('@param'))
	for match in matches:
		count +=1
		print str(count) + " of " + length + " training examples done"
	
		if match in CORRECT_LIST:
			#print calc_freq.term_freq('tweet',words_per_file[match])
			train_f.write('+1')
			#print len(words_per_file[match])
		else:
			train_f.write('-1')
		
		train_f.write(str(get_word_counts(words_per_file[match], words, calc_freq)))
		train_f.write('\n')
	train_f.close()	
	print "Results printed to file: " + str(TRAIN)
	
# this is by far not the best way to do this
def get_word_counts(file_words, all_words, calc_freq):
	word_count_dict = dict()
	# initialize word dictionary
	word_2_num_trans = list()
	words_f = open(WORDS, 'w')
	for word in all_words:	
		word_count_dict[word.lower()] = 0
		words_f.write(word.lower()+'\n')
	words_f.close()
	for w in file_words:
		w = w.lower()
		#if w == 'tweet':
		#	print calc_freq.calc_tfidf(w,file_words)
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

if __name__=='__main__':
	process_files(sys.argv[1])


