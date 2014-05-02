
    public TreeNode getRoot() {
        TreeNode ancestor = this;
        TreeNode previous;

        do {
            previous = ancestor;
            ancestor = ancestor.getParent();
        } while (ancestor != null);

        return previous;
    }


    