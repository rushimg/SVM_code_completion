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