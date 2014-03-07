import subprocess
import os, sys
import re
'''
File to parse out types and variable names from code 

Elementry way of doing this based on regexes

Eclipse Plugin can do this for us

'''
# Match CamelCase with first letter Capital
OBJECT_REGEX = '([A-Z][a-z0-9]+)+'
PRIMITIVE_TYPES = ['int', 'Int', 'String', 'string', 'byte', 'short', 'long', 'float','double','boolean', 'char']

obj_reg = re.compile(OBJECT_REGEX)
	
def run_parser(in_f):
	# get all the text
	f = open(in_f, 'r')
	raw_text = f.read()
	f.close()

	text = clean(raw_text)
	spaces = text.split(' ')

	# get all the lines
	f = open(in_f, 'r')
	text_lines = f.readlines()
	f.close()

	print "----------------Original Code-----------------------"
	
	print raw_text
	print '\n'
	
	print "-----------------Classes----------------------------"
	# TODO add class and insides
	class_count = 0
	classes = dict()
	for space in spaces:
		if space == 'class':
			# get next word after class
			#classes.append(spaces[class_count+1])
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
                                        start_curly +=1
                                        start_flag = True
                                if start_flag == True:
                                        if (start_curly == end_curly):
                                                break
                                        else:
                                                encapsulated += (sub_space +' ')
			classes[spaces[class_count+1]] = encapsulated
		class_count += 1
	print classes
	'''classes = dict()	
	for line in text_lines:
		if "class" in line:
			line = line.replace('{','')
			line_spaces = line.split(' ')
			for line_space in line_spaces:
				if line_space == class
			print line'''
		
	print '\n'
	print "-------------Var Types and Names: -------------------"
	var_types = dict()	
	types_count = 0
	for space in spaces:
		#if ((not space == ' ') and (not space == '\n')):
		if (obj_reg.match(space) and (space not in classes) and ('(' not in space) and ('{' not in spaces[types_count+1] )):
			var_types[spaces[types_count + 1]] = space 	
		types_count += 1
	
	print var_types

	print '\n'
	print "-------------------Methods--------------------------"
	
	methods = dict()
        method_count = 0
        for space in spaces:
                # TODO: a bit hacky when saying that the previous word should not be 'new'
		if (obj_reg.match(space) and (space not in classes) and ('(' in space) and (spaces[method_count-1] != 'new')):
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
					start_curly +=1
					start_flag = True
				if start_flag == True:
					if (start_curly == end_curly):
						break
					else:
						encapsulated += (sub_space +' ')	

			methods[space] = encapsulated
                method_count += 1
        print methods

	#print "------------------ Code Pieces: ---------------------" 	
	#for space in spaces:
	#	print space
	print '\n'
	print "------------------ Encapsulated Segments -----------"
	
	#encap_str = re.compile('\{*\}')
	match = re.search(r'{([^{}]*)}',text)
	#match = re.findall(r'/{([^}]*)}/',text)
	print match.group()
	#print match.group()
	#code_segments = encap_str.search(text) 
	#print code_segments.group(0)
	#print code_segments.group(1)
	print ' ' 
def clean(raw_text):
	clean_text = raw_text
	#clean_text = raw_text.replace('(',' ')
	#clean_text = clean_text.replace(')',' ')
	clean_text = clean_text.replace(',',' ')
	clean_text = clean_text.replace('  ',' ')
	clean_text = clean_text.replace('  ',' ')
	return clean_text

if __name__=='__main__':
        run_parser(sys.argv[1])
