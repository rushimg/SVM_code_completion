private static boolean isPrime(int x) {
      assert x > 1;
      int top = (int)Math.sqrt(x);
      for (int i = 2; i <= top; i++)
         if ( x % i == 0 )
            return false;
      return true;
   }
