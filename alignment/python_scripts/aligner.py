import subprocess
import os, sys
import re
'''
Class containing algorithm to align two methods as input and output a score {0 to 1} determining their similarity
'''

class aligner:
	def __init__(self, input_f1, input_f2):
		self.vars_1 = input_f1
		self.vars_2 = input_f2

	'''
	def run_aligner(input_f_1, input_f_2):
	f1 = eval((open(input_f_1,'r')).read())
	f2 = eval((open(input_f_2,'r')).read())	
	align(f1,f2)
	'''
	
	def align(self):
		return self.measure_var_numbers(self.vars_1,self.vars_2)

	# measure number of overlapping vars over total number of vars
	def measure_var_numbers(self,f1,f2):
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
	
	'''
	if __name__=='__main__':
        run_aligner(sys.argv[1], sys.argv[2])
	'''
