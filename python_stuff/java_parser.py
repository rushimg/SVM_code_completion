import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*.java"
DIR_NAME = "../../aws-sdk-for-java"
OUT_DIR_NAME = "../code_corpus/train/aws_train_code/"

def process_files():
	matches = []
        for root, dirnames, filenames in os.walk(DIR_NAME):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))

	counter = 0
	for match in matches:
		print match
		extract_doc_and_code(match,counter)
		counter += 1

def extract_doc_and_code(match,counter):
	f = open(match, 'r')
	endstr=match.split('/')[-1]
	print endstr
	lines = f.readlines()
	counter = 0
	for line in lines:
		#if "@param" in line or "@result" in line:
		#	print "JDOC"
		#comment = ""
		if "/*" in line:
			comment=line
		elif "*/" in line:
			counter += 1
			comment+=line
			out = open(OUT_DIR_NAME+endstr + "_" +str(counter),'w')
			out.write(comment)
			out.close()
			#print comment
		elif "*" in line:
			comment +=line

if __name__=='__main__':
	process_files()


