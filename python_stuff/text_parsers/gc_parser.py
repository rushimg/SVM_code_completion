import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

#MATCH = "*.java"
#DIR_NAME = "../../twitter4j"
#DIR_NAME = "../../aws-sdk-for-java"
#OUT_DIR_NAME = "../code_corpus/aws/"

def process_files(match):
	
	print "PROCESSING FILE: " + match
	extract_doc_and_code(match)

def extract_doc_and_code(match):
	f = open(match, 'r')
	endstr=match.split('/')[-1]
	out_dir = match.replace(endstr,'')
	text = f.read()
	sections = text.split('/**')
	for sect in sections:
		print "SECTION"
		end_comment = sect.split('*/')
		print "COMMENT"
		print '/**' + end_comment[0] + '*/'
		print "CODE"
		print end_comment[1]
		code_lines = end_comment[1].split('\n')
		#print repr(code_lines[0])
		if code_lines[0].replace(' ','').replace('\r','') == '':
			#print "here"
			signature = code_lines[1]
		else:
			signature = code_lines[0]
		print "SIGNATURE " + clean(signature)
	
		
		 	
		f_out_code = open(out_dir+clean(signature)+'.java','w')
		f_out_code.write(end_comment[1])
		f_out_code.close()

		f_out_com = open(out_dir+clean(signature)+'.comment','w')
                f_out_com.write('/**' + end_comment[0] + '*/')
                f_out_com.close()

		#signature.replace('(',' ').replace(')',' ').replace('{','').r
	#print out_dir
			
def clean(in_str):
	slash = in_str.split('/')
	out_str = slash[0]
	out_str = out_str.replace('(',' ')
	out_str = out_str.replace(')',' ')
	out_str = out_str.replace(',',' ')
	out_str = out_str.replace('{','')
	out_str = out_str.replace('}',' ')
	out_str = out_str.replace('[]','List')
	out_str = out_str.strip()
	out_str = out_str.replace(' ','_')
	out_str = out_str.replace('__','_')
	return out_str

if __name__=='__main__':
	process_files(sys.argv[1])


