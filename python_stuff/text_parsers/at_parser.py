import sys,os,re
import fnmatch
import xml.etree.ElementTree as ET

MATCH = "*"
DIR_NAME = "../../code_corpus/aws/"
OUT_DIR_NAME = "../../code_corpus/aws_at/"
at_tags = ["param","throws","see","return","link"]

def process_files():
	matches = []
        for root, dirnames, filenames in os.walk(DIR_NAME):
                for filename in fnmatch.filter(filenames, MATCH):
                        matches.append(os.path.join(root, filename))

	for match in matches:
		print "PROCESSING FILE: " + match
		extract_at(match)

def extract_at(match):
	f = open(match, 'r')
	comment = f.read()
	at = comment.split('@')
	new_words = ""
        if len(at) > 1:
		group_counter = 0
		for group in at:
			spaces = group.split(' ')
                        head_word = spaces[0]
	        	
			if (group_counter == 0) and (head_word not in at_tags):
				new_words += group
			
			elif head_word in at_tags:
				for word in spaces[1:]:
					if not (word == "" or word == "*" or word == " " or word == "\n"): 
						new_words += " " + '@'+head_word+word				
			group_counter += 1
	else:
		new_words = comment

	out_file_n = match.replace(DIR_NAME,OUT_DIR_NAME)
	f_out = open(out_file_n, 'w')
	f_out.write(new_words)
	f_out.close()
	
if __name__=='__main__':
	process_files()


