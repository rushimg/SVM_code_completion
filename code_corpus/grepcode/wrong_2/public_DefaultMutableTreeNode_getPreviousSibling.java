
    public DefaultMutableTreeNode getPreviousSibling() {
        DefaultMutableTreeNode retval;

        DefaultMutableTreeNode myParent = (DefaultMutableTreeNode)getParent();

        if (myParent == null) {
            retval = null;
        } else {
            retval = (DefaultMutableTreeNode)myParent.getChildBefore(this);     // linear search
        }

        if (retval != null && !isNodeSibling(retval)) {
            throw new Error("child of parent is not a sibling");
        }

        return retval;
    }



    //
    //  Leaf Queries
    //

    