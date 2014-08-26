import sys
from bs4 import BeautifulSoup
import csv
def process_comments(in_f,out_f):
	out = open(out_f,'w')
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter=',')
		for row in reader:	
			id = row[0]
			print id
			soup = BeautifulSoup(row[1])
			# get code
			all_methods = list()
			for p in soup.find_all("code"):
				code = p.text
				for line in code.split('\n'):
					all_methods += get_methods(line)
			
			# get everything other than code -> "comments"
			[s.extract() for s in soup('script')]
			comments = soup.get_text().replace('\n','').replace('\t','').encode('ascii', 'ignore')
			print comments
			#out.write(id + '\t' + str(all_methods) + '\t'+ comments + '\n') 
# TODO: do this less hacky
def get_methods(line):
	methods = list()
	period = line.split('.')
	#print 'Original Line : ' + line
	if "import " not in line: 
		if len(period) > 1:
			open_bracket = period[1].split('(')
			if len(open_bracket) > 0:
				methods.append(open_bracket[0])
	return methods

if __name__ == "__main__":
	process_comments(sys.argv[1],sys.argv[2])
	
