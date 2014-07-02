import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*"
DIR_NAME = "../../code_corpus/python/HtmlCleaner/"
def process_files():
	matches = []
        for root, dirnames, filenames in os.walk(DIR_NAME):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))

	for match in matches:
		#print "PROCESSING FILE: " + match
		f = open(match, 'r').read()
		f = f.replace(' ','').replace('\n','').replace('\t','').replace('\r','')
		if f == '':
			os.remove(match)
			print match
		#extract_doc_and_code(match)
if __name__=='__main__':
	process_files()


