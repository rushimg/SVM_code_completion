
    public DefaultMutableTreeNode getPreviousNode() {
        DefaultMutableTreeNode previousSibling;
        DefaultMutableTreeNode myParent = (DefaultMutableTreeNode)getParent();

        if (myParent == null) {
            return null;
        }

        previousSibling = getPreviousSibling();

        if (previousSibling != null) {
            if (previousSibling.getChildCount() == 0)
                return previousSibling;
            else
                return previousSibling.getLastLeaf();
        } else {
            return myParent;
        }
    }

    