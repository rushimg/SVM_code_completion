
    protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
        TreeNode[]              retNodes;

        /* Check for null, in case someone passed in a null node, or
           they passed in an element that isn't rooted at root. 