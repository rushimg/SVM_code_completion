
    public DefaultMutableTreeNode getNextLeaf() {
        DefaultMutableTreeNode nextSibling;
        DefaultMutableTreeNode myParent = (DefaultMutableTreeNode)getParent();

        if (myParent == null)
            return null;

        nextSibling = getNextSibling(); // linear search

        if (nextSibling != null)
            return nextSibling.getFirstLeaf();

        return myParent.getNextLeaf();  // tail recursion
    }


    