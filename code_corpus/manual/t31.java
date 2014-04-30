private static void doPrintCommand() throws ParseError {
      double val = expressionValue();
      TextIO.skipBlanks();
      if ( TextIO.peek() != '\n' )
         throw new ParseError("Extra data after end of expression.");
      TextIO.putln("Value is " + val);
   }
