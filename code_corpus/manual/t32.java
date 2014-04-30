private static double expressionValue() throws ParseError {
      TextIO.skipBlanks();
      boolean negative;  // True if there is a leading minus sign.
      negative = false;
      if (TextIO.peek() == '-') {
         TextIO.getAnyChar();
         negative = true;
      }
      double val;  // Value of the expression.
      val = termValue();  // An expression must start with a term.
      if (negative)
         val = -val; // Apply the leading minus sign
      TextIO.skipBlanks();
      while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
            // Read the next term and add it to or subtract it from
            // the value of previous terms in the expression.
         char op = TextIO.getAnyChar();
         double nextVal = termValue();
         if (op == '+')
            val += nextVal;
         else
            val -= nextVal;
         TextIO.skipBlanks();
      }
      return val;
   } // end expressionValue()
