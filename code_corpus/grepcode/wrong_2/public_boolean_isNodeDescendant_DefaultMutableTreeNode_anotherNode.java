
    public boolean isNodeDescendant(DefaultMutableTreeNode anotherNode) {
        if (anotherNode == null)
            return false;

        return anotherNode.isNodeAncestor(this);
    }

    