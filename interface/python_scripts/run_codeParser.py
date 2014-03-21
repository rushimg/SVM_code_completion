#! /usr/local/bin/python

from codeParser import codeParser
	
f1_n = '../tests/source_1.java'
#f1_n = '../../code_corpus/regular_code/XmlResponsesSaxParser.java_30_code_mod'
t = codeParser(f1_n)
print 'Original Code: \n' +  t.get_orginalCode()
print '\n'
print 'classes: ' + str(t.get_classes())
print '\n'
print 'variables: ' + str(t.get_varTypes())
print '\n'
print 'methods: ' + str(t.get_methods())
print '\n'
print 'statements: ' + str(t.get_statements())




#	print var.get_usage()
