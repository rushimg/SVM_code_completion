private static void birthdayProblem() {

      boolean[] used;

      int count;

      used = new boolean[365];
      
      count = 0;

      while (true) {
         int birthday;
         birthday = (int)(Math.random()*365);
         count++;
         if ( used[birthday] )
            break;
         used[birthday] = true;
      }

      System.out.println("A duplicate birthday was found after " 
                                                + count + " tries.");

   }
