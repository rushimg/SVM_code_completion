#! /usr/local/bin/python

from codeParser import codeParser
from aligner import aligner

f1_n = '../../code_corpus/search_code/sort_merged_1.java'
f2_n = '../../code_corpus/search_code/sort_merged_2.java'

t = aligner(f1_n,f2_n)

print "Cost: " + str(t.methodSignature())
