from aligner import aligner
	
f1 = {'otherVar':'otherType','stringOne': 'String', 'boolOne ': 'boolean', 'intOne': 'int', 'otherIntOne':'int'} 
f2 = {'intTwo': 'int', 'boolTwo ': 'boolean', 'stringTwo': 'String'} 

t = aligner(f1,f2)
print t.align()
