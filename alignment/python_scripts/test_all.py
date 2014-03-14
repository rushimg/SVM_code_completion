from codeParser import codeParser
from aligner import aligner

f1_n = '../raw_code/test_1'
f2_n = '../raw_code/test_2'

#f1_n = '../../code_corpus/regular_code/XmlResponsesSaxParser.java_30_code_mod'
t1 = codeParser(f1_n)
t2 = codeParser(f2_n)

vars1 = t1.get_varTypes()
vars2 = t2.get_varTypes()


t = aligner(vars1,vars2)
print t.align()
