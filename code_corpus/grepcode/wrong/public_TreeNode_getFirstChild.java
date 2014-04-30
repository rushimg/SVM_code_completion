
    public TreeNode getFirstChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return getChildAt(0);
    }


    