import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

'''
Look at already proceesed sdk methods and create a lookup table relating method name-> full method code-> comments assoicated with it
'''
MATCH = '*.java*'
def process_files(in_dir,out_f):
	matches = []
        for root, dirnames, filenames in os.walk(in_dir):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))

	for match in matches:
		print "PROCESSING FILE: " + match
		extract_info(match)

def extract_info(match):
	f = open(match,'r')
	for line in f.readlines():
		if '{' in line:
			print line

if __name__=='__main__':
	process_files(sys.argv[1], sys.argv[2])


