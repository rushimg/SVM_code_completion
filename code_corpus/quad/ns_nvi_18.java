public boolean find(String searchItem) {

      Node runner;

      runner = head;
      
      while ( runner != null ) {
         if ( runner.item.equals(searchItem) )
            return true;
         runner = runner.next;
      }


      return false;

   }
