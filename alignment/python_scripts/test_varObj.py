from codeParser import codeParser
	
f1_n = '../raw_code/test_1'
#f1_n = '../../code_corpus/regular_code/XmlResponsesSaxParser.java_30_code_mod'
t = codeParser(f1_n)

counter = 0
for var in  t.get_listOf_variableObj():
	print '\n'
	print "Variable " + str(counter) + ' : '
	print 'Type: ' + var.get_type()
	print 'Name: ' + var.get_name()
	print 'Declaration: ' + var.get_declaration()
	print 'Usage: ' + str(var.get_usage())
	counter += 1
