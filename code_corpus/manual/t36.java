private void String readWord(String word) {
      char ch = TextIO.peek();
      while (Character.isLetter(ch) || Character.isDigit(ch)) {
         word += TextIO.getChar(); // Add the character to the word.
         ch = TextIO.peek();
      }
      return word;
   }
