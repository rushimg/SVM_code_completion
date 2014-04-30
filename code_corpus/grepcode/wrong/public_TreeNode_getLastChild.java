
    public TreeNode getLastChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return getChildAt(getChildCount()-1);
    }


    