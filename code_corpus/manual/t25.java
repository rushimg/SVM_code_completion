private static double factorValue() throws ParseError {
      TextIO.skipBlanks();
      char ch = TextIO.peek();
      if ( Character.isDigit(ch) ) {
             // The factor is a number.
         return TextIO.getDouble();
      }
      else if ( ch == '(' ) {
             // The factor is an expression in parentheses.
         TextIO.getAnyChar();  // Read the "("
         double val = expressionValue();
         TextIO.skipBlanks();
         if ( TextIO.peek() != ')' )
            throw new ParseError("Missing right parenthesis.");
         TextIO.getAnyChar();  // Read the ")"
         return val;
      }
      else if ( ch == '\n' )
         throw new ParseError("End-of-line encountered in the middle of an expression.");
      else if ( ch == ')' )
         throw new ParseError("Extra right parenthesis.");
      else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
         throw new ParseError("Misplaced operator.");
      else
         throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
   }

