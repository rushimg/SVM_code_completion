
    public int getIndex(TreeNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild);        // linear search
    }

    