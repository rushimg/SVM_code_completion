from variableObj import variableObj
import subprocess
import os, sys
import re
'''
In Object Oriented Manner

File to parse out types and variable names from code 

Elementry way of doing this based on regexes

Eclipse Plugin can do this for us

'''

# Match CamelCase with first letter Capital
OBJECT_REGEX = '([A-Z][a-z0-9]+)+'
PRIMITIVE_TYPES = ['int', 'Int', 'String', 'string', 'byte', 'short', 'long', 'float','double','boolean', 'char']
ACCESS_MODIFIERS = ['public','private','protected']
STATEMENTS = ['while','if','for']
obj_reg = re.compile(OBJECT_REGEX)
	

class codeParser:

	def __init__(self,input_f1):
		self.spaces = self.open_file(input_f1)
		self.parse_classes()
		self.parse_varTypes()
		self.parse_methods()
		self.parse_statements()	
		self.listOf_varObj = list()
		self.set_listOf_variableObj()

	def open_file(self,in_f):
		# get all the text
		f = open(in_f, 'r')
		lines = f.readlines()
		f.close()

		# clean out comments 
		raw_text = ''
		for line in lines:
			line.replace(' ','')
			#if line.startsWith('//')
			#if ('//' in line) and not('http' in line):
			#	slashes = line.split('//')
		#		raw_text += slashes[0]
			if not '//' in line:
				raw_text += line
		self.raw_text = raw_text
		text = self.clean(raw_text)
		spaces = text.split(' ')
		return spaces

 	def get_orginalCode(self):
		return self.raw_text
	
	def parse_classes(self):
		spaces = self.spaces
	
		class_count = 0
		classes = dict()
		for space in spaces:
			if (space == 'class'):
				# get next word after class	
				# get enclaspulated code by matching number of end and start curly brackets
                        	encapsulated = ''
                       		start_flag = False
                        	start_curly = 0
                        	end_curly = 0
                        	for sub_space in spaces[class_count:len(spaces)]:
			        	if '{' in sub_space:
                                        	start_curly+=1
                                	elif '}' in sub_space:
                                        	end_curly +=1
                                	if ('{' in sub_space) and (not start_flag):
                                        	#start_curly +=1
                                        	start_flag = True
                                	if start_flag == True:
                                        	if (start_curly == end_curly):
                                                	break
                                        	else:
                                                	encapsulated += (sub_space +' ')
				classes[spaces[class_count+1]] = encapsulated
			class_count += 1
		self.classes =  classes
	
	def get_classes(self):
                return  self.classes
             
	def parse_varTypes(self):
		spaces = self.spaces
		var_types = dict()	
		types_count = 0
	
		for space in spaces:
			#if ((not space == ' ') and (not space == '\n')):
			#('(' not in space)
			#(spaces[types_count -1] not in ACCESS_MODIFIERS)
			if ((obj_reg.match(space) or (space in PRIMITIVE_TYPES) or ('java.util' in space)) and (space not in self.classes) and ('(' not in space) and ('(' not in spaces[types_count+1]) and ('{' not in spaces[types_count+1] )):
				var_types[self.remove_space(self.replace_paren(spaces[types_count + 1]))] = space
			types_count += 1
		
		self.var_types = var_types

	def get_varTypes(self):
		return self.var_types


	def set_listOf_variableObj(self):
		for key in self.var_types:
			temp_varObj = variableObj(key,self.var_types[key],self.raw_text,self.classes,self.methods,self.statements)
			self.listOf_varObj.append(temp_varObj)
	
	def get_listOf_variableObj(self):
		return self.listOf_varObj

	def parse_methods(self):
		spaces = self.spaces	
		methods = dict()
        	method_count = 0
       		for space in spaces:
                	# TODO: a bit hacky when saying that the previous word should not be 'new'
			#obj_reg.match(space)
			if ((space not in self.classes) and ('(' in space) and (spaces[method_count-1] != 'new') and ('.' not in space) and (self.replace_paren(space) != ' ')):
                        	encapsulated = ''
				start_flag = False
				start_curly = 0
				end_curly = 0
				for sub_space in spaces[method_count:len(spaces)]:
					# get enclaspulated code by matching number of end and start curly brackets
					if '{' in sub_space:
						start_curly+=1
					elif '}' in sub_space:
						end_curly +=1 
					if ('{' in sub_space) and (not start_flag):
						start_flag = True
					if start_flag == True:
						if (start_curly == end_curly):
							break
						else:
							encapsulated += (sub_space +' ')	
				if (encapsulated != ''):
					methods[self.replace_paren(space)] = encapsulated
                	method_count += 1
		self.methods = methods
	
	def get_methods(self):
		return self.methods
		
	def parse_statements(self):
        	spaces = self.spaces
		statements = dict()
        	statement_count = 0
        	
		for space in spaces:
                	if ((space not in self.classes) and (spaces[statement_count-1] != 'new') and ('.' not in space) and (space in STATEMENTS)):
                        	encapsulated = ''
                        	start_flag = False
                        	start_curly = 0
                        	end_curly = 0
                        	for sub_space in spaces[statement_count:len(spaces)]:
                                	# get enclaspulated code by matching number of end and start curly brackets
                                	if '{' in sub_space:
                                        	start_curly+=1
                                	elif '}' in sub_space:
                                        	end_curly +=1
                                	if ('{' in sub_space) and (not start_flag):
                                        	#start_curly +=1
                                        	start_flag = True
                                	if start_flag == True:
                                        	if (start_curly == end_curly):
                                                	break
                                        	else:
                                                	encapsulated += (sub_space +' ')

                        	statements[self.replace_paren(space)] = encapsulated
                	statement_count += 1
       		self.statements = statements
	
	def get_statements(self):
		return self.statements
	
	def replace_paren(self,raw_text):
		clean_text = raw_text
        	clean_text = raw_text.replace('(',' ')
        	clean_text = clean_text.replace(')',' ')
		clean_text = clean_text.replace('\n','')
		clean_text = clean_text.replace(';','')
		return clean_text
	
	def remove_space(self,str_in):
		str_out = str_in.replace(' ','')
		return str_out

	def clean(self,raw_text):
		clean_text = raw_text
		clean_text = clean_text.replace('++',' ++ ')
		clean_text = clean_text.replace(';',' ;')
		clean_text = clean_text.replace('--',' -- ')
		clean_text = clean_text.replace('(','( ')
		clean_text = clean_text.replace(')',' ) ')
		clean_text = clean_text.replace(',',' ')
		clean_text = clean_text.replace('\t',' ')
		clean_text = clean_text.replace('  ',' ')
		clean_text = clean_text.replace('  ',' ')
		return clean_text
