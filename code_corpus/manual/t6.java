static void printRowsFromString( String str ) {
      int i;
      for ( i = 0; i < str.length(); i++ ) {
         printRow( str.charAt(i), 25 );
      }
   }
