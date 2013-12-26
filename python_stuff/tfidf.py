from math import log

class tfidf:

	def __init__(self, terms, documents):
		self.idfs = self.inv_doc_freq(terms, documents)

	def calc_tfidf(self,term,document):
		tf = self.term_freq(term,document)
		idf = self.idfs[term]
		tf_idf = tf*idf
		return tf_idf
	
	def term_freq(self,term,document):
		tf_count = 0
		length_doc = len(document)
		for word in document:
			if word == term:
				tf_count += 1
		tf = float(tf_count)/float(length_doc)
		return tf

	'''
	we can pre-caluclate the idfs of each term 
	so that we dont have to continuously recalculate them each time 
	'''
	def inv_doc_freq(self,terms,document_terms):
		idf_dict = dict()
		num_docs = len(document_terms)
		for term in terms:
			in_docs = 0	
			for document in document_terms:
				if term in document_terms[document]:
					in_docs += 1	
			if in_docs != 0:
				idf_dict[term] = log(float(num_docs)/float(in_docs),10)	
		return idf_dict
	
	def getIDF(self,term):
		return self.idfs[term]




