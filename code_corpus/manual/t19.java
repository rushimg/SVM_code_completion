private static int countNodes(TreeNode node) {
      if ( node == null ) {
            // Tree is empty, so it contains no nodes.
         return 0;
      }
      else {
            // Add up the root node and the nodes in its two subtrees.
         int leftCount = countNodes( node.left );
         int rightCount = countNodes( node.right );
         return  1 + leftCount + rightCount;  
      }
   } // end count
