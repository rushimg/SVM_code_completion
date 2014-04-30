static boolean treeContains( TreeNode root, String item ) {
      if ( root == null ) {
             // Tree is empty, so it certainly doesn't contain item.
         return false;
      }
      else if ( item.equals(root.item) ) {
             // Yes, the item has been found in the root node.
         return true;
      }
      else if ( item.compareTo(root.item) < 0 ) {
             // If the item occurs, it must be in the left subtree.
         return treeContains( root.left, item );
      }
      else {
             // If the item occurs, it must be in the right subtree.
         return treeContains( root.right, item );
      }
   }  // end treeContains()
