 private static ExpNode termTree() throws ParseError {
      TextIO.skipBlanks();
      ExpNode term;  // The expression tree representing the term.
      term = factorTree();
      TextIO.skipBlanks();
      while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
             // Read the next factor, and combine it with the
             // previous factors into a bigger expression tree.
         char op = TextIO.getAnyChar();
         ExpNode nextFactor = factorTree();
         term = new BinOpNode(op,term,nextFactor);
         TextIO.skipBlanks();
      }
      return term;
   } // end termValue()
