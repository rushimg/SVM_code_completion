'''
Randomly remove lines from the CSV file to check whether trimming the search space has any effect
'''

import random
import csv,sys,os
import fnmatch

def trim(in_f, out_f, factor):
	f = open(out_f, 'w')
	with open(in_f, 'rb') as csvfile:
    		reader = csv.reader(csvfile, delimiter='\t')
		for row in reader:
			if row[1] == '0' or row[1] == '1':
				rand = random.randint(1, int(factor))
				if rand == int(factor):
					for el in row:
						f.write(el+'\t') 
					f.write('\n')
			
			else:
				for el in row:
					f.write(el+'\t')
				f.write('\n')
	
	f.close()

if __name__ == '__main__':
	in_f = sys.argv[1]
	out_f = sys.argv[2]
	factor = sys.argv[3]
	trim(in_f,out_f,factor)


