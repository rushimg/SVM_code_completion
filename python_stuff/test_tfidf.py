from tfidf import tfidf


terms = ('Rushi','Mahesh','Ganmukhi')
documents = {
'doc1': ['Rushi','Mahesh'],
'doc2':['Mahesh'],
'doc3':['Ganmukhi']}


t = tfidf(terms,documents)
print t.calc_tfidf('Mahesh',['Mahesh','Rushi','Mahesh'])




