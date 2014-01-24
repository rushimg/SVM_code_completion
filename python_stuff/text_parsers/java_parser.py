import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*.java"
DIR_NAME = "../../../sdks/restfb-1.6.12/"
OUT_DIR_NAME = "../../code_corpus/facebook/"

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
	endstr=match.split('/')[-1]
	lines = f.readlines()
	counter = 0
	comment_pointer = ''
	code = ''
	comment = ""
	for line in lines:
		'''
		if "@param" in line or "@result" in line:
			print "JDOC"
		comment = ""
		'''
		'''
		if "/*" in line and "*/" in line:
			comment=line
			out = open(OUT_DIR_NAME+endstr + "_" +str(counter),'w')
                        out.write(comment)
			out.close()
			comment = ""
			comment_pointer = OUT_DIR_NAME+endstr + "_" +str(counter)
		'''
		if "/*" in line:
			if code != '':
				code_f = open(comment_pointer + '_code', 'w')
				code_f.write(code)
				code_f.close()
				code = ''
			comment=line
		elif "*/" in line:
			counter += 1
			comment+=line
			out = open(OUT_DIR_NAME+endstr + "_" +str(counter),'w')
			out.write(comment)
			out.close()
			comment = ""
			comment_pointer = OUT_DIR_NAME+endstr + "_" +str(counter)
		elif "*" in line:
			comment +=line
		else:
			code += line
					
	
if __name__=='__main__':
	process_files()


