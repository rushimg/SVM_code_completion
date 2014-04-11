#! /usr/local/bin/python

from codeParser import codeParser
from aligner import aligner

f1_n = '../../code_corpus/search_code/sort_merged_1.java'
#f2_n = '../../code_corpus/search_code/sort_merged_2.java'
#f2_n = '../../code_corpus/manual/fibo_1.java'
#f1_n = '../../code_corpus/manual/fibo_3.java'
f2_n = '../../code_corpus/search_code/sort_merged_1_test.java'

t = aligner(f1_n,f2_n)

#COST
print "Cost: " + str(t.methodSignature())

# TRANSFORMATION
transform_2_to_1, transform_1_to_2 = t.transformation()
print "Transformation 2 to 1: " + str(transform_2_to_1)
print "Transformation 1 to 2: " + str(transform_1_to_2)
