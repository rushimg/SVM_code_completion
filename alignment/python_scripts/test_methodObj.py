#! /usr/local/bin/python

from codeParser import codeParser
	
f1_n = '../raw_code/test_1'
#f1_n = '../../code_corpus/regular_code/XmlResponsesSaxParser.java_30_code_mod'
t = codeParser(f1_n)

print "Testing " + f1_n + ' \n'
counter = 1
for var in  t.get_listOf_methodObj():
	print '\n'
	print "Method " + str(counter) + ':'
	print 'Output[return] Type: ' + var.getOutput()
	print 'Input Parameters: ' + var.getInput()
	print 'Input ParametersTypes: ' + str(var.getInputTypes())
	print 'Encapsulated Code: ' + var.getEncapsulatedCode()
	counter += 1
