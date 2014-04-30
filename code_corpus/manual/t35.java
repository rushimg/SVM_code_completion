private static double primaryValue() throws ParseError {
      TextIO.skipBlanks();
      char ch = TextIO.peek();
      if ( Character.isDigit(ch) ) {
            // The factor is a number.  Read it and
            // return its value.
         return TextIO.getDouble();
      }
      else if ( Character.isLetter(ch) ) {
            // The factor is a variable.  Read its name and
            // look up its value in the symbol table.  If the
            // variable is not in the symbol table, an error
            // occurs.  (Note that the values in the symbol
            // table are objects of type Double.)
         String name = readWord();
         Double val = symbolTable.get(name);
         if (val == null)
            throw new ParseError("Unknown variable \"" + name + "\"");
         return val.doubleValue();
      }
      else if ( ch == '(' ) {
            // The factor is an expression in parentheses.
            // Return the value of the expression.
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
      else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/')
         throw new ParseError("Misplaced operator.");
      else
         throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
   }

