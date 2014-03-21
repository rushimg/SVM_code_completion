#! /usr/local/bin/python

import os, sys
from codeParser import codeParser
	
#f1_n = '../tests/source_1.java'

def run(f1_n):
	t = codeParser(f1_n)
	print "Testing " + f1_n + ' \n'
	counter = 1
	for var in  t.get_listOf_variableObj():
		print '\n'
		print "Variable " + str(counter) + ':'
		print 'Type: ' + var.get_type()
		print 'Name: ' + var.get_name()
		print 'Declaration: ' + var.get_declaration()
		print 'Usage: ' + str(var.get_usage())
		counter += 1

if __name__=='__main__':
        run(sys.argv[1]) 
