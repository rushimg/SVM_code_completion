import subprocess
import os, sys
import fnmatch
import csv
from codeParser import codeParser
from aligner import aligner

MAX_COST = 5
'''
run the cost based classifier
final output line is the accuracy
'''
def read_test_data(in_dir):
	f_query = in_dir+'query'	
	f_map = open(in_dir+'map','r')
	#f_query = open(in_dir+'query','r')
	#f_test_corr = open(in_dir+'test_corr','r')	
	#f_test_wrong = open(in_dir+'test_wrong','r')
	#f_train_corr = open(in_dir+'train_corr','r')
	#correct_files = f_train_corr.readlines()

	dict_test_corr = dict()
	dict_test_wrong = dict()
	dict_test_all = dict()
	svm_positive = list()

	#query = f_query.read()
	# hack to make a method signature into a method
	#query = query + '{ }' 
	#t = codeParser(in_dir+'query')
	#counter = 1
	#for var in  t.get_listOf_methodObj():
        	#print '\n'
        	#print "Method " + str(counter) + ':'
       		#print 'Output[return] Type: ' + var.getOutput()
        	#print 'Input Parameters: ' + var.getInput()
       		#print 'Input ParametersTypes: ' + str(var.getInputTypes())
        	#print 'Encapsulated Code: ' + var.getEncapsulatedCode()
        	#counter += 1	
	#for_test = ['code_corpus/grepcode/corr/public_static_void_quickSort_ObjectList_a.java', 'code_corpus/grepcode/corr/public_static_void_quickSort_ObjectList_a_Comparator_c.java']	
	cost_list = dict()
	for line in f_map.readlines():	
		slash = line.split('../')
		rank = float(slash[0].strip())
		if rank > .85:
			f = '../'+(slash[1].strip())
			#f = '../../'+(slash[1].strip())
			f = f.replace('regular/aws','regular_code')
			f = f +'_code'
			#print f
			#svm_positive.append('../'+(slash[1].strip()))
			cost_list[f] = calculate_cost(f,f_query)					
	sorted_cost_list = sorted(cost_list, key=cost_list.get)
	for elem in sorted_cost_list[1:10]:
		print elem + ' : ' + str(cost_list[elem])
	
def calculate_cost(f_test,f_query):
	#print f_test
	#print f_query	
	cost = 1000000
	try:
		t = aligner(f_test,f_query)
		cost = t.methodSignature()
	except:
		pass

	#if cost == 0:
	#print f_test
	#print cost
	return cost

if __name__=='__main__':
	read_test_data(sys.argv[1])
	
