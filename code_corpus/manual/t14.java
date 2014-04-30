public String[] getElements() {

      int count;          // For counting elements in the list.
      Node runner;        // For traversing the list.
      String[] elements;  // An array to hold the list elements.

      // First, go through the list and count the number
      // of elements that it contains.

      count = 0;
      runner = head;
      while (runner != null) {
         count++;
         runner = runner.next;
      }

      // Create an array just large enough to hold all the
      // list elements.  Go through the list again and
      // fill the array with elements from the list.

      elements = new String[count];
      runner = head;
      count = 0;
      while (runner != null) {
         elements[count] = runner.item;
         count++;
         runner = runner.next;
      }

      // Return the array that has been filled with the list elements.

      return elements;

   } // end getElements()
