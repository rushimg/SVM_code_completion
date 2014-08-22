import sys
from bs4 import BeautifulSoup
import csv
def process_comments(in_f):
	with open(in_f, 'rb') as csvfile:
		reader = csv.reader(csvfile, delimiter=',')
		for row in reader:	
			print row[1]
			#print row[0]
			#for p in BeautifulSoup(row[1]).find_all("code"):
        		# 	print p.text


	
			#print row[1]
		
	#raw_html = open(in_f, 'r').read()
	#soup = BeautifulSoup(raw_html)
	#rows = soup.find_all("row")
	#for p in rows:
	#	print p.text
if __name__ == "__main__":
	process_comments(sys.argv[1])
	
