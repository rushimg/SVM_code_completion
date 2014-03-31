'''
Interface parser

Parse user created interface files and return

method_name, javadoc_comment, input_vars, output_vars for each interfaced method
'''


class interfaceParser:

	def __init__(self, input_f1):
		self.text_lines = open(input_f1,'r').readlines()

	
	def getJavaDocComments(self):
		
		comments_dict = dict()
		code_dict = dict()
		code_list = list()	
		comment = ''
		code = ''	
		counter = 0
		lines_counter = 0
		for line in self.text_lines:
			if "/*" in line:
                        	comment=line
                	elif "*/" in line:
                        	comment+=line	
				comments_dict[str(counter)] = comment
				counter += 1
				comment = ""	
				#code_list.append(code)
				#code = ''					
                	elif "*" in line:
                        	comment +=line
                	#else:
                        	#code += line
			lines_counter += 1

		lines_counter = 0
		for line in self.text_lines:
                        # TODO lines with multiplication will be caught here, need to avodi this bug 
			if not("/*" in line) and not("*/" in line) and not("*" in line):
                                code += line	
				#print code
			else:
				code_dict[str(counter)] = code
				code = ''
                    	lines_counter += 1

		print code_dict
		return comments_dict
	
	def cleanMethodName(self,methodDefinitionLine):
		return methodDefinitionLine
