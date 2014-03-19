import subprocess
import os, sys
import re

'''
Variable Object containg data pertaing to variable info such as name, type, usage, context, scope
'''

class variableObj:
	
	def __init__(self, name, varType,code,classes,methods,statements):
		self.name = name
		self.varType = varType
		self.raw_code = code
		self.declaration_usage()
		self.classes = classes
		self.methods = methods
		self.statements = statements 

	## Probably dont need these
	#''' code precding the variable usage '''
	#def preceding_code(self):
	#''' code succeeding the variable usage '''
	#def succeeding_code(self):
	
	''' variable type '''
	def get_type(self):
		return self.varType
	
	## Add these in later
	#''' all code that is in the scope of the variable '''
	#def scope(self):
	#	#for use in self.usage():		
	#''' all statements that variable is a part of '''
	#def statements(self):
	#''' dependencies of variables '''
	#def dependencies(self):
	

	''' usage of variable '''
	def get_usage(self):
		return self.usage

	''' differentiate between usage & declaration of variable '''
	def declaration_usage(self):
		self.declaration = ''
		self.usage = list()
		code = self.clean(self.raw_code)
		lines = code.split('\n')
		for line in lines:
			if self.name in line:
				#print line
				spaces = line.split(' ')
				space_counter = 0
				for space in spaces:
					space = self.remove_space(space)
					if space_counter > 0 and space_counter < len(spaces):
						if (space == self.name) and (spaces[space_counter-1] == self.varType):
							self.declaration = line
			
						elif (space == self.name) and (spaces[space_counter-1] != self.varType): 
							self.usage.append(line)
					space_counter += 1
	
	''' declaration of variable ''' 
	def get_declaration(self):
		return self.declaration
	
	''' name of varibale '''
	def get_name(self):
		return self.name
	
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
		clean_text = clean_text.replace(',',' , ')
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

