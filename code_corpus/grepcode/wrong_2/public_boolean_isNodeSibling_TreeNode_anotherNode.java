
    public boolean isNodeSibling(TreeNode anotherNode) {
        boolean retval;

        if (anotherNode == null) {
            retval = false;
        } else if (anotherNode == this) {
            retval = true;
        } else {
            TreeNode  myParent = getParent();
            retval = (myParent != null && myParent == anotherNode.getParent());

            if (retval && !((DefaultMutableTreeNode)getParent())
                           .isNodeChild(anotherNode)) {
                throw new Error("sibling has different parent");
            }
        }

        return retval;
    }


    