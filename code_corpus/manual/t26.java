private static ExpNode expressionTree() throws ParseError {
      TextIO.skipBlanks();
      boolean negative;  // True if there is a leading minus sign.
      negative = false;
      if (TextIO.peek() == '-') {
         TextIO.getAnyChar();
         negative = true;
      }
      ExpNode exp;       // The expression tree for the expression.
      exp = termTree();  // Start with the first term.
      if (negative)
         exp = new UnaryMinusNode(exp);
      TextIO.skipBlanks();
      while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
             // Read the next term and combine it with the
             // previous terms into a bigger expression tree.
         char op = TextIO.getAnyChar();
         ExpNode nextTerm = termTree();
         exp = new BinOpNode(op, exp, nextTerm);
         TextIO.skipBlanks();
      }
      return exp;
   } // end expressionTree()
