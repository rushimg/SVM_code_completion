private static void readAndEvaluate() {

      StackOfDouble stack;  // For evaluating the expression.

      stack = new StackOfDouble();  // Make a new, empty stack.

      TextIO.putln();

      while (TextIO.peek() != '\n') {

         if ( Character.isDigit(TextIO.peek()) ) {
                // The next item in input is a number.  Read it and
                // save it on the stack.
            double num = TextIO.getDouble();
            stack.push(num);
            TextIO.putln("   Pushed constant " + num);
         }
         else {
                // Since the next item is not a number, the only thing
                // it can legally be is an operator.  Get the operator
                // and perform the operation.
            char op;  // The operator, which must be +, -, *, /, or ^.
            double x,y;     // The operands, from the stack, for the operation.
            double answer;  // The result, to be pushed onto the stack.
            op = TextIO.getChar();
            if (op != '+' && op != '-' && op != '*' && op != '/' && op != '^') {
                   // The character is not one of the acceptable operations.
               TextIO.putln("\nIllegal operator found in input: " + op);
               return;
            }
            if (stack.isEmpty()) {
               TextIO.putln("   Stack is empty while trying to evaluate " + op);
               TextIO.putln("\nNot enough numbers in expression!");
               return;
            }
            y = stack.pop();
            if (stack.isEmpty()) {
               TextIO.putln("   Stack is empty while trying to evaluate " + op);
               TextIO.putln("\nNot enough numbers in expression!");
               return;
            }
            x = stack.pop();
            switch (op) {
            case '+':  
               answer = x + y; 
               break;
            case '-':  
               answer = x - y;  
               break;
            case '*':  
               answer = x * y;  
               break;
            case '/':  
               answer = x / y;  
               break;
            default:   
               answer = Math.pow(x,y);  // (op must be '^'.)
            }
            stack.push(answer);
            TextIO.putln("   Evaluated " + op + " and pushed " + answer);
         }

         TextIO.skipBlanks();

      }  // end while

      // If we get to this point, the input has been read successfully.
      // If the expression was legal, then the value of the expression is
      // on the stack, and it is the only thing on the stack.

      if (stack.isEmpty()) {  // Impossible if the input is really non-empty.
         TextIO.putln("No expression provided.");
         return;
      }

      double value = stack.pop();  // Value of the expression.
      TextIO.putln("   Popped " + value + " at end of expression.");

      if (stack.isEmpty() == false) {
         TextIO.putln("   Stack is not empty.");
         TextIO.putln("\nNot enough operators for all the numbers!");
         return;
      }

      TextIO.putln("\nValue = " + value);


   } // end readAndEvaluate()
