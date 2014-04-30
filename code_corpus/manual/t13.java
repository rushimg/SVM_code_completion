public void insert(String insertItem) {

      Node newNode;          // A Node to contain the new item.
      newNode = new Node();
      newNode.item = insertItem;  // (N.B.  newNode.next is null.)

      if ( head == null ) {
             // The new item is the first (and only) one in the list.
             // Set head to point to it.
         head = newNode;
      }
      else if ( head.item.compareTo(insertItem) >= 0 ) {
             // The new item is less than the first item in the list,
             // so it has to be inserted at the head of the list.
         newNode.next = head;
         head = newNode;
      }
      else {
             // The new item belongs somewhere after the first item
             // in the list.  Search for its proper position and insert it.
         Node runner;     // A node for traversing the list.
         Node previous;   // Always points to the node preceding runner.
         runner = head.next;   // Start by looking at the SECOND position.
         previous = head;
         while ( runner != null && runner.item.compareTo(insertItem) < 0 ) {
                // Move previous and runner along the list until runner
                // falls off the end or hits a list element that is
                // greater than or equal to insertItem.  When this 
                // loop ends, previous indicates the position where
                // insertItem must be inserted.
            previous = runner;
            runner = runner.next;
         }
         newNode.next = runner;     // Insert newNode after previous.
         previous.next = newNode;
      }

   }

