import subprocess
import os, sys
import re

'''
Method Object containg data pertaing to method info such as name,return type, input params, and encapsulated code
'''

class methodObj:
	
	def __init__(self, name):
		self.name = name
		

	def setEncapsulatedCode(self,code):
		self.encapsulatedCode = code

	def getEncapsulatedCode(self):
		return self.encapsulatedCode

	def setOutput(self,out):
		self.output = out

	def getOutput(self):
		return self.output

	def setInput(self,inp):
		self.input = inp	
	
	def getInput(self):
		return self.input


	def getInputTypes(self):
		inputs = self.getInput()
		counter = 0
		raw_list = list()
		for space in inputs.split(' '):
			#print space
			if counter % 2 == 0:
				if (space != '') and (space != ' '):
					raw_list.append(space)
			counter += 1
		return raw_list
