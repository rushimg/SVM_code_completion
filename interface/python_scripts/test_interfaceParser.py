#! /usr/local/bin/python

from interfaceParser import interfaceParser
	
f1_n = '../tests/interface_1.java'
t = interfaceParser(f1_n)
c_dict = t.getJavaDocComments()

for code in c_dict:
	print code
	print c_dict[code] 
	
'''
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

'''