import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*_both"
#DIR_NAME = "../../../sdks/pyrax/pyrax"
OUT_DIR_NAME = "../../code_corpus/python/pyrax/"
DIR_NAME = OUT_DIR_NAME
'''
Split already parsed python methods
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
	f = open(match, 'r')
	text = f.read()
	quotes = text.split('"""')
	#print quotes
	if len(quotes) == 3:
		code = quotes[0] + quotes[2]
		comments = '"""'+quotes[1]+'"""'
	else:
		comments = ""
		code = text
	code_f = match.replace('_both','_code')
	open(code_f,'w').write(code)

	com_f = match.replace('_both','_com')
        open(com_f,'w').write(comments)


	#endstr=match.split('/')[-1]
	
	#lines = f.readlines()
	#counter = 0
	#comment_pointer = ''
	#code = ''
	#comment_flag = False
	#tabs = 0
	"""
	for line in lines:
		
		if 'def ' in line:
			tabs = line.count("\t")
			# hack to get around the fact that when code is downloaded the tabs change to spaces in python
			if tabs == 0:
				tabs = line.split('def')[0].count(' ')
			#print code
			print tabs
			if code != '':
				counter +=1 
                                print OUT_DIR_NAME+endstr + '_'+str(counter) + '_code'
				code_f = open(OUT_DIR_NAME+endstr + '_'+str(counter) + '_code', 'w')
                                code_f.write(code)
                                code_f.close()
                                code = ''
				tabs = 0
			comment_flag = not comment_flag
		# check to see if line is still in method
		if comment_flag and line.split(line.replace(' ',''))[0].count(' ') > tabs:	
			code += line
	"""	
if __name__=='__main__':
	process_files()


