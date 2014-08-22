import sys
from bs4 import BeautifulSoup
import csv
def process_comments(in_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter=',')
		for row in reader:	
			id = row[0]
			soup = BeautifulSoup(row[1])
			# get code
			for p in soup.find_all("code"):
        		 	print "==================================="
				code = p.text
				for line in code.split('\n'):
					get_methods(line)
			
			# get everything other than code -> "comments"
			[s.extract() for s in soup('script')]
			comments = soup.get_text()
			
		
def get_methods(line):
	period = line.split('.')
	print 'Original Line : ' + line
	if "import " not in line: 
		if len(period) > 1:
			open_bracket = period[1].split('(')
			if len(open_bracket) > 0:
				print open_bracket[0] 
				#+ ' : ' + line

	#print ' : ' + line

if __name__ == "__main__":
	process_comments(sys.argv[1])
	
