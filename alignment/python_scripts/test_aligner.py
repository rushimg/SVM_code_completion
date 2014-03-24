#! /usr/local/bin/python

from aligner import aligner
	
f1 = {'otherVar':'otherType','stringOne': 'String', 'boolOne ': 'boolean', 'intOne': 'int', 'otherIntOne':'int'} 
f2 = {'intTwo': 'int', 'boolTwo ': 'boolean', 'stringTwo': 'String'} 
f3 = {'somethingThree':'notAnInt', 'somthingElseThree':'boolean'}
t = aligner(f2,f1,'','')
print t.align()
