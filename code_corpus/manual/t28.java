private static ExpNode factorTree() throws ParseError {
      TextIO.skipBlanks();
      char ch = TextIO.peek();
      if ( Character.isDigit(ch) ) {
             // The factor is a number.  Return a ConstNode.
         double num = TextIO.getDouble();
         return new ConstNode(num);
      }
      else if ( ch == '(' ) {
             // The factor is an expression in parentheses.
             // Return a tree representing that expression.
         TextIO.getAnyChar();  // Read the "("
         ExpNode exp = expressionTree();
         TextIO.skipBlanks();
         if ( TextIO.peek() != ')' )
            throw new ParseError("Missing right parenthesis.");
         TextIO.getAnyChar();  // Read the ")"
         return exp;
      }
      else if ( ch == '\n' )
         throw new ParseError("End-of-line encountered in the middle of an expression.");
      else if ( ch == ')' )
         throw new ParseError("Extra right parenthesis.");
      else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
         throw new ParseError("Misplaced operator.");
      else
         throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
   }  // end factorTree()
