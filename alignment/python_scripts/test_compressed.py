#! /usr/local/bin/python

from codeParser import codeParser
f1_n = '../../code_corpus/search_code/cat_sm'	
#f1_n = '../raw_code/test_1'
#f1_n = '../../code_corpus/regular_code/XmlResponsesSaxParser.java_30_code_mod'
t = codeParser(f1_n)

print t.get_compressed()

