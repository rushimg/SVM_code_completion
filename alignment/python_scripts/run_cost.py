import subprocess
import os, sys
import fnmatch
import csv
from codeParser import codeParser
from aligner import aligner

MAX_COST = 5

def read_test_data(in_dir):
	f_test_corr = open(in_dir+'test_corr','r')	
	f_test_wrong = open(in_dir+'test_wrong','r')
	f_train_corr = open(in_dir+'train_corr','r')
	correct_files = f_train_corr.readlines()

	dict_test_corr = dict()
	dict_test_wrong = dict()
	
	for line in f_test_corr.readlines():
		# hacky adjustment to relative path so that old test files can be run in this script
		line = line.replace('\n','')
		line = '../'+line
		print line
		dict_test_corr[line] = calculate_cost(line, correct_files, True)	
	print 'CORRECT'
	print dict_test_corr

	for line in f_test_wrong.readlines():     
                line = line.replace('\n','')
		line = '../'+line
		print line
		dict_test_wrong[line] = calculate_cost(line, correct_files, False)

	print ' WRONG' 
	print dict_test_wrong	


	MAX_COST = calculate_margin(dict_test_corr,dict_test_wrong)
	num_correct = 0
	for corr in dict_test_corr:
		if dict_test_corr[corr] <= MAX_COST:
			num_correct += 1
	
	for wrong in dict_test_wrong:
                if dict_test_wrong[wrong] > MAX_COST:
                        num_correct += 1
	
	print float(num_correct)/float(len(dict_test_corr)+len(dict_test_wrong))
 
def calculate_margin(dict_test_corr,dict_test_wrong):
	sum_correct=0
	sum_wrong = 0
	count_correct = 0
    	count_wrong = 0
	max_correct = 0
	min_wrong = 1000
	for corr in dict_test_corr:
                #print dict_test_corr[corr]        
		if isinstance(dict_test_corr[corr], float):
			sum_correct += dict_test_corr[corr]
			count_correct += 1
			if dict_test_corr[corr] > max_correct:
				max_correct = dict_test_corr[corr]
	avg_corr = float(sum_correct)/float(count_correct)
	#print "CORR"
	#print "avg: " + str(avg_corr)
	#print count_correct
	#print len(dict_test_corr)
	for wrong in dict_test_wrong:
		#print (dict_test_wrong[wrong])
		if isinstance(dict_test_wrong[wrong], float):
                       	sum_wrong += dict_test_wrong[wrong]
			count_wrong += 1 
 			if dict_test_wrong[wrong] < min_wrong:
                                min_wrong = dict_test_wrong[wrong]

	avg_wrong = float(sum_wrong)/float(count_wrong)
	#print "WRONG"
        #print "avg: " + str(avg_wrong)
	#print 'total avg: ' + str(float(avg_wrong+avg_corr)/float(2))
	print min_wrong
	print max_correct
	return float(max_correct+min_wrong)/float(2)
	#return float(avg_wrong+avg_corr)/float(2)
		
def calculate_cost(f_test,f_originals,bool_correct):
	'''
	Iterate through all training code and return min cost to correct or max cost to incorrect
	'''
	cost_list = list()
	for original in f_originals:
		original = '../'+original.replace('\n','')
		if original != '../':
			t = aligner(f_test,original)
			try:
				cost_list.append(t.methodSignature())
			except (IndexError,ZeroDivisionError) as e:
				pass
	# we want to return the max cost for incorrext and the min cost for correct
	if len(cost_list) > 0:
		if bool_correct:
			return min(cost_list)
		else:
			return max(cost_list)
	else:
		return 'N/A'
if __name__=='__main__':
	read_test_data(sys.argv[1])
	
