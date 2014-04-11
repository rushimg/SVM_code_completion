from variableObj import variableObj
from methodObj import methodObj
import subprocess
import os, sys
import re
from codeParser import codeParser
'''
Class containing algorithm to align two methods as input and output a score {0 to 1} determining their similarity
'''

# TODO: filter so that every file only contains one method not named main
# TODO: Parse out comments
class aligner:
	# change this to take in variable object and method objects 
	def __init__(self,input_f1, input_f2):
		self.vars_1 = (codeParser(input_f1)).get_varTypes()
                self.vars_2 = (codeParser(input_f2)).get_varTypes()
		

		self.vars_list_1 = (codeParser(input_f1)).get_listOf_variableObj()
		self.vars_list_2 = (codeParser(input_f2)).get_listOf_variableObj()
		self.method_list_1 = (codeParser(input_f1)).get_listOf_methodObj()
		self.method_list_2 = (codeParser(input_f2)).get_listOf_methodObj()
		self.lines_1 = open(input_f1,'r').readlines()
		self.lines_2 = open(input_f2,'r').readlines()

	'''
	Match on return type and parameters
	'''
	def methodSignature(self):
		methods_1 = self.method_list_1
		methods_2 = self.method_list_2
		#print self.vars_list_1
		#print metho
		cost = 0
		
		# We assume one method per file so this situation should never occur
		if (len(methods_1) != 1 or len(methods_2) != 1):
			cost += 100
		if (methods_1[0].getOutput() !=  methods_2[0].getOutput()):
			cost += 5

		method_dict_1 = dict()
		counter = 0
		for in_type in methods_1[0].getInputTypes():
			method_dict_1[str(counter)] = in_type
			counter += 1
		counter = 0		
		method_dict_2 = dict()  
                for in_type in methods_2[0].getInputTypes():
                        method_dict_2[str(counter)] = in_type
         		counter += 1
		cost += 5*(1-self.measure_difference(method_dict_1,method_dict_2))
		
		return cost
	
	'''
	Same as transformation but takes as inputs the output dictionaries of transformation
	'''
	def compare_transformationi(transform_1, transform_2):
		return transform_1, transform_2

	''' 
	define 	the difference between two methods in terms of input/return types 
	return what signature of next function to be called should be
	'''
	def transformation(self):
		methods_1 = self.method_list_1
                methods_2 = self.method_list_2
		transform_1_to_2 = dict()
		transform_2_to_1 = dict()
		# assume only one method per file
		
		if (methods_1[0].getOutput() !=  methods_2[0].getOutput()):
                        transform_2_to_1['output'] = methods_1[0].getOutput()
			transform_1_to_2['output'] = methods_2[0].getOutput()
		else:
			transform_2_to_1['output'] = ''
                        transform_1_to_2['output'] = ''

		transform_2_to_1['input'] = methods_1[0].getInputTypes()
		transform_1_to_2['input'] = methods_2[0].getInputTypes() 
		
		for in_type in methods_2[0].getInputTypes():
			if in_type in transform_2_to_1['input']:
				 transform_2_to_1['input'].remove(in_type)

		for in_type in methods_1[0].getInputTypes():
                        if in_type in transform_1_to_2['input']:
                                 transform_1_to_2['input'].remove(in_type)
		print "output 1: " + methods_1[0].getOutput()
		print "input 1: " + str(methods_1[0].getInputTypes())
		print "output 2: " + methods_2[0].getOutput()
		print "input 2: " + str(methods_2[0].getInputTypes())	
		return transform_2_to_1, transform_1_to_2
	
	'''
	TODO:
		Alignment Levels:
		1) Method level -> inputs/outputs
		2) Statement Level -> Number and types of statements
		3) Variable Level -> Number and types of variables, variable context
		4) Line level -> similarity between functionalitites of lines 
	'''
	
	def matching_lines(self):
		no_space_lines_f2 = []
		no_space_lines_f1 = []
		lines_f1 = self.lines_1
		lines_f2 = self.lines_2
		
		for line in self.lines_1:
			no_space_lines_f1.append(self.rm_space_newLine(line))
		for line in self.lines_2:
                        no_space_lines_f2.append(self.rm_space_newLine(line))
           
		matching_lines  = set()
                for line in lines_f2:
			temp_line = self.rm_space_newLine(line)
			if (temp_line in no_space_lines_f1) and (temp_line != '{' and temp_line != '}'):
                                # set of no space no newline lines
				matching_lines.add(temp_line)

		for line in lines_f1:
                        temp_line = self.rm_space_newLine(line)
                        if (temp_line in no_space_lines_f2) and (temp_line != '{' and temp_line != '}'):
                                matching_lines.add(temp_line)

		return matching_lines

	''' fuzzy matching between lines '''
	def fuzzy_matching_lines(self):	
		no_space_lines_f2 = []
                no_space_lines_f1 = []
                lines_f1 = self.lines_1
                lines_f2 = self.lines_2

                for line in self.lines_1:
                        no_space_lines_f1.append(self.rm_space_newLine(line))
                for line in self.lines_2:
                        no_space_lines_f2.append(self.rm_space_newLine(line))

                matching_lines  = set()
		# TODO: compare longer to shorter
                for line2 in lines_f2:
			spaces_2 = set(line2.split(' '))
			for line1 in lines_f1:
				spaces_1 = set(line1.split(' '))
				if (self.jaccard_dist(spaces_1,spaces_2) < .5):
					print "%-80s %s" % (line1.replace('\n',''), line2.replace('\n',''))
					#try:
					#	lines_f1.remove(line1)
					#	lines_f2.remove(line2)
					#except Exception:
					#	pass
				
				#print line1

				#print line2
				#print self.jaccard_dist(spaces_1,spaces_2)		

	''' detect which variables are similar between files and replace both with the same generic name '''
	def variable_replacement(self):
		var_1 = self.vars_list_1
		var_2 = self.vars_list_2

		counter = 0
		for var in  var_1:
        		print '\n'
        		print "Variable " + str(counter) + ':'
        		print 'Type: ' + var.get_type()
        		print 'Name: ' + var.get_name()
        		print 'Declaration: ' + var.get_declaration()
       			print 'Usage: ' + str(var.get_usage())
			counter += 1

		counter = 0
                for var in  var_2:
                        print '\n'
                        print "Variable " + str(counter) + ':'
                        print 'Type: ' + var.get_type()
                        print 'Name: ' + var.get_name()
                        print 'Declaration: ' + var.get_declaration()
                        print 'Usage: ' + str(var.get_usage())
                        counter += 1
				
	def print_alignment(self):
		ml = self.matching_lines()
		max_count = max(len(self.lines_1), len(self.lines_2))
		# pad lines
		lines_f1 = self.lines_1 + [''] * (max_count - len(self.lines_1))	
		lines_f2 = self.lines_2 + [''] * (max_count - len(self.lines_2))  
		
		print "%-80s %s" % ('file1','file2')
		
		for i in range(0,max_count):
			print "%-80s %s" % (lines_f1[i].replace('\n',''), lines_f2[i].replace('\n',''))
			#print lines_f1[i].replace('\n','') + ' ' + lines_f2[i].replace('\n','')

	def rm_space_newLine(self,str_in):
		str_out = (str_in.replace(' ','')).replace('\n','')
		str_out = str_out.replace('{','')
		str_out = str_out.replace('}','')
		return str_out

	def align(self):
		return self.measure_var_numbers()

	def disagreement_dist(self, a, b):
		if a == b:
			return 0
		else: 
			return 1

	def jaccard_dist(self,a,b):
		union = a.union(b)
		inter = a.intersection(b)
		len_union = float(len(union))
		len_inter = float(len(inter))
		jsim = len_inter/len_union
		jdist = 1-jsim
    		return jdist
		
	def measure_difference(self,f1,f2):
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
		
	def measure_var_numbers(self):
		f1 = self.vars_1
		f2 = self.vars_2
		return self.measure_difference(f1,f2)	
	
	# edit distance functio
	#def editDistance(str1,str2):
		
		
