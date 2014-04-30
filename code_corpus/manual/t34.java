private static double factorValue() throws ParseError {
      TextIO.skipBlanks();
      double val;  // Value of the factor.
      val = primaryValue();  // A factor must start with a primary.
      TextIO.skipBlanks();
      while ( TextIO.peek() == '^' ) {
            // Read the next primary, and exponentiate
            // the value-so-far by the value of this primary.
         TextIO.getChar();
         double nextVal = primaryValue();
         val = Math.pow(val,nextVal);
         if (Double.isNaN(val))
            throw new ParseError("Illegal values for ^ operator.");
         TextIO.skipBlanks();
      }
      return val;
   } // end termValue()
