private static int countPrimes(int min, int max) {
      int count = 0;
      for (int i = min; i <= max; i++)
         if (isPrime(i))
            count++;
      return count;
   }
