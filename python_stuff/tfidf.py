
class tfidf:
	def __init__(self, terms, documents):
		self.corpus = documents
		self.idfs = pre_calc_idfs(terms, documents)

	def calc_tfidf(term,document):
		tf = calc_tf(term,document)
		idf = calc_idf(term,document)
		tf_idf = tf*idf
		return tf_idf
	
	def calc_tf(term,document):
		return tf

	def calc_idf(term,document):
		return idf

	'''we can pre-caluclate the idfs of each term 
	so that we dont have to continuously recalculate them each time'''
	def pre_calc_idf(terms,documents):
		for term in terms:	
			for document in documents:
				
