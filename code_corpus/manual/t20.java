 private static double expressionValue() throws ParseError {
      TextIO.skipBlanks();
      if ( Character.isDigit(TextIO.peek()) ) {
             // The next item in input is a number, so the expression
             // must consist of just that number.  Read and return
             // the number.
         return TextIO.getDouble();
      }
      else if ( TextIO.peek() == '(' ) {
             // The expression must be of the form 
             //         "(" <expression> <operator> <expression> ")"
             // Read all these items, perform the operation, and
             // return the result.
         TextIO.getAnyChar();  // Read the "("
         double leftVal = expressionValue();  // Read and evaluate first operand.
         char op = getOperator();             // Read the operator.
         double rightVal = expressionValue(); // Read and evaluate second operand.
         TextIO.skipBlanks();
         if ( TextIO.peek() != ')' ) {
                // According to the rule, there must be a ")" here.
                // Since it's missing, throw a ParseError.
            throw new ParseError("Missing right parenthesis.");
         }
         TextIO.getAnyChar();  // Read the ")"
         switch (op) {   //  Apply the operator and return the result. 
         case '+':  return leftVal + rightVal;
         case '-':  return leftVal - rightVal;
         case '*':  return leftVal * rightVal;
         case '/':  return leftVal / rightVal;
         default:   return 0;  // Can't occur since op is one of the above.
                               // (But Java syntax requires a return value.)
         }
      }
      else {
         throw new ParseError("Encountered unexpected character, \"" + 
               TextIO.peek() + "\" in input.");
      }
   } // end expressionValue()
