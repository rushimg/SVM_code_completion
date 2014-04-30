static void playGame() {
       int computersNumber;
       int usersGuess;
       int guessCount;
       computersNumber = (int)(100 * Math.random()) + 1;
       guessCount = 0;
       TextIO.putln();
       TextIO.put("What is your first guess? ");
       while (true) {
          usersGuess = TextIO.getInt();
          guessCount++;
          if (usersGuess == computersNumber) {
             TextIO.putln("You got it in " + guessCount
                     + " guesses!  My number was " + computersNumber);
             break;
          }
          if (guessCount == 6) {
             TextIO.putln("You didn't get the number in 6 guesses.");
             TextIO.putln("You lose.  My number was " + computersNumber);
             break;
          }
          if (usersGuess < computersNumber)
             TextIO.put("That's too low.  Try again: ");
          else if (usersGuess > computersNumber)
             TextIO.put("That's too high.  Try again: ");
       }
       TextIO.putln();
   }
