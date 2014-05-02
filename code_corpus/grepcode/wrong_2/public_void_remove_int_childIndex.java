
    public void remove(int childIndex) {
        MutableTreeNode child = (MutableTreeNode)getChildAt(childIndex);
        children.removeElementAt(childIndex);
        child.setParent(null);
    }

    