private static double termValue() throws ParseError {
      TextIO.skipBlanks();
      double val;  // The value of the term.
      val = factorValue();  // A term must start with a factor.
      TextIO.skipBlanks();
      while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            // Read the next factor, and multiply or divide
            // the value-so-far by the value of this factor.
         char op = TextIO.getAnyChar();
         double nextVal = factorValue();
         if (op == '*')
            val *= nextVal;
         else
            val /= nextVal;
         TextIO.skipBlanks();
      }
      return val;
   } // end termValue()    * Read an expression from the current line of input and return its value.
   
