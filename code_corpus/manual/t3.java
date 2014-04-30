public static void tThree(String[] args) {
      
      Day tgif;
      Month libra;
      
      tgif = Day.FRIDAY;
      libra = Month.OCT;
      
      System.out.print("My sign is libra, since I was born in ");
      System.out.println(libra); 
      System.out.print("That's the ");
      System.out.print( libra.ordinal() );
      System.out.println("-th month of the year.");
      System.out.println("   (Counting from 0, of course!)");
      
      System.out.print("Isn't it nice to get to ");
      System.out.println(tgif); 
      
      System.out.println( tgif + " is the " + tgif.ordinal() 
            + "-th day of the week.");
      
   }
