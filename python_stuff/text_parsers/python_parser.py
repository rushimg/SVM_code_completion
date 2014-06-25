import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*.py"
DIR_NAME = "../../../sdks/pyrax/pyrax"
OUT_DIR_NAME = "../../code_corpus/python/pyrax/"
#TABS = 4
'''
Split python sdk code and comments by method
'''
def process_files():
	matches = []
        for root, dirnames, filenames in os.walk(DIR_NAME):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))

	for match in matches:
		print "PROCESSING FILE: " + match
		extract_doc_and_code(match)

def extract_doc_and_code(match):
	#tabs = TABS
	f = open(match, 'r')
	endstr=match.split('/')[-1]
	lines = f.readlines()
	counter = 0
	comment_pointer = ''
	code = ''
	comment_flag = False
	tabs = 0
	proc_lines = []
	for line in lines:
		
		if line != '\n' and not(line.lstrip().startswith('@')):
			#print line
			proc_lines.append(line)

	for line in proc_lines:
		if 'def ' in line:
			#code += line
			#tabs = line.count("\t")
			# hack to get around the fact that when code is downloaded the tabs change to spaces in python
			#if tabs == 0:
			#	print line
			start = line.lstrip()
			tabs = line.split(start)[0].count(' ')
			#print tabs

			if code != '':
				counter +=1 
                                print OUT_DIR_NAME+endstr + '_'+str(counter) + '_both'
				code_f = open(OUT_DIR_NAME+endstr + '_'+str(counter) + '_both', 'w')
				
				code = ('\n').join(code.split('\n')[1:])
				code_f.write(code)
                                code_f.close()
                                code = ''
				tabs = 0
			#print 'line: '+ line
			code += line
			comment_flag = True
			
		#print (len(line) - len(line.lstrip()))
		#print (len(line) - len(line.lstrip())) > tabs
		#print line.lstrip()
		#line = line.replace('\t','')
		#if len(line.replace(' ','').replace('\n','')) == 0:
		#	print "here"
		#	pass # skip emptylines
		# check to see if line is still in method
		#elif comment_flag and line.split(line.replace(' ',''))[0].count(' ') > tabs:
		#if line.strip() == '' or line.strip() == '\n':
		#	pass
		if comment_flag and (len(line) - len(line.lstrip())) > tabs:
			#print (len(line) - len(line.lstrip()))
			#print line.split(line.replace(' ',''))[0].count(' ')	
			#print line.replace(' ','_')
			#print line.split(line.replace(' ',''))[0]
			#print tabs
			#print (len(line) - len(line.lstrip()))
			#print line
			code += line
		elif comment_flag and (len(line) - len(line.lstrip())) <= tabs:
			#if line.strip() != '' and line.strip() != '\n' and code != '':
			
					
			comment_flag = False
			tabs = 0
		
if __name__=='__main__':
	process_files()


