#! /usr/local/bin/python

from codeParser import codeParser
from aligner import aligner

f1_n = '../../code_corpus/search_code/sort_merged_1.java'
f2_n = '../../code_corpus/search_code/sort_merged_2.java'

t = aligner(f1_n,f2_n)

print "Matching lines: " + str(t.matching_lines())
print "Measure Var Numbers: " + str(t.measure_var_numbers())
print '-------------------------------------------------------'
print t.print_alignment()
#print t.fuzzy_matching_lines()
print t.variable_replacement()
