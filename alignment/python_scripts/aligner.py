import subprocess
import os, sys
import re
from variableObject import variableObject
'''
Class containing algorithm to align two methods as input and output a score {0 to 1} determining their similarity
'''

class aligner:
	# change this to take in variable object and method objects 
	def __init__(self, vars_f1, vars_f2, methods_f1, methods_f2):
		self.vars_1 = vars_f1
		self.vars_2 = vars_f2
		self.methods_1 = methods_f1
		self.methods_2 = methods_f2


	def align(self):
		return self.measure_var_numbers()

	# what else could we put?
	# edit distance with alignment and shifting? between var names
	# jaccard of similar code context?
	# if both in same method/class
	# if both in if/for/while statment/loop
	# measure number of overlapping vars over total number of vars
	
	#TODO
	# replace vars with stubbed generic names
	# align pieces of code and find the difference 
	# also need to add in a methods parser

	def measure_var_numbers(self):
		f1 = self.vars_1
		f2 = self.vars_2
		f1_count = dict()
		f2_count = dict()
		keys = set()
		matched = 0
		
		for key in f1:
			type_temp = f1[key]
			if not (type_temp in f1_count.keys()):
 				f1_count[type_temp] = 1
				keys.add(type_temp)
			else:
				f1_count[type_temp] += 1
		for key in f2:
                	type_temp = f2[key]
                	if not (type_temp in f2_count.keys()):
                        	f2_count[type_temp] = 1
				keys.add(type_temp)
                	else:
                        	f2_count[type_temp] += 1
	
		for key in keys:
			if (key in f1_count) and (key in f2_count):
				matched += min(f1_count[key],f2_count[key])
		total = len(f1) + len(f2) - matched
	
		return float(matched)/float(total)
	
	# edit distance functio
	#def editDistance(str1,str2):
		
		
