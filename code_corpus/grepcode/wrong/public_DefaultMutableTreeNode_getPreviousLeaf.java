
    public DefaultMutableTreeNode getPreviousLeaf() {
        DefaultMutableTreeNode previousSibling;
        DefaultMutableTreeNode myParent = (DefaultMutableTreeNode)getParent();

        if (myParent == null)
            return null;

        previousSibling = getPreviousSibling(); // linear search

        if (previousSibling != null)
            return previousSibling.getLastLeaf();

        return myParent.getPreviousLeaf();              // tail recursion
    }


    