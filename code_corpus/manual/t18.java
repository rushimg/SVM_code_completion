private static void treeList(TreeNode node) {
      if ( node != null ) {
         treeList(node.left);             // Print items in left subtree.
         TextIO.putln("  " + node.item);  // Print item in the node.
         treeList(node.right);            // Print items in the right subtree.
      }
   } // end treeList()
