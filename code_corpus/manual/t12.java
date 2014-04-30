public void delete(String deleteItem) {

      if ( head == null ) {
         return false;
      }
      else if ( head.item.equals(deleteItem) ) {
         head = head.next;
         return true;
      }
      else {
         Node runner;
         Node previous;
         runner = head.next;
         previous = head;
         while ( runner != null && runner.item.compareTo(deleteItem) < 0 ) {
            previous = runner;
            runner = runner.next;
         }
         if ( runner != null && runner.item.equals(deleteItem) ) {
            previous.next = runner.next;
            return true;
         }
         else {
            return false;
         }
      }

   }
