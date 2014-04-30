public static void tOne(String[] args) {
      
      double principal;     
      double rate;         
      double interest;      
      
      
      principal = 17000;
      rate = 0.07;
      interest = principal * rate;
      
      principal = principal + interest;
      
      
      System.out.print("The interest earned is $");
      System.out.println(interest);
      System.out.print("The value of the investment after one year is $");
      System.out.println(principal);
      
   }
