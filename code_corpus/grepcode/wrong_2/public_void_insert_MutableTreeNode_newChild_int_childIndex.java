
    public void insert(MutableTreeNode newChild, int childIndex) {
        if (!allowsChildren) {
            throw new IllegalStateException("node does not allow children");
        } else if (newChild == null) {
            throw new IllegalArgumentException("new child is null");
        } else if (isNodeAncestor(newChild)) {
            throw new IllegalArgumentException("new child is an ancestor");
        }

            MutableTreeNode oldParent = (MutableTreeNode)newChild.getParent();

            if (oldParent != null) {
                oldParent.remove(newChild);
            }
            newChild.setParent(this);
            if (children == null) {
                children = new Vector();
            }
            children.insertElementAt(newChild, childIndex);
    }

    