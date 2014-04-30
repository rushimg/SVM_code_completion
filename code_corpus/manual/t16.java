private static void treeInsert(String newItem) {
      if ( root == null ) {
             // The tree is empty.  Set root to point to a new node containing
             // the new item.  This becomes the only node in the tree.
         root = new TreeNode( newItem );
         return;
      }
      TreeNode runner;  // Runs down the tree to find a place for newItem.
      runner = root;   // Start at the root.
      while (true) {
         if ( newItem.compareTo(runner.item) < 0 ) {
                // Since the new item is less than the item in runner,
                // it belongs in the left subtree of runner.  If there
                // is an open space at runner.left, add a new node there.
                // Otherwise, advance runner down one level to the left.
            if ( runner.left == null ) {
               runner.left = new TreeNode( newItem );
               return;  // New item has been added to the tree.
            }
            else
               runner = runner.left;
         }
         else {
                // Since the new item is greater than or equal to the item in
                // runner it belongs in the right subtree of runner.  If there
                // is an open space at runner.right, add a new node there.
                // Otherwise, advance runner down one level to the right.
            if ( runner.right == null ) {
               runner.right = new TreeNode( newItem );
               return;  // New item has been added to the tree.
            }
            else
               runner = runner.right;
         }
      } // end while
   }  // end treeInsert()
