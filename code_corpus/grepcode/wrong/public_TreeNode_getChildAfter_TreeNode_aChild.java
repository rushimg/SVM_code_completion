
    public TreeNode getChildAfter(TreeNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        int index = getIndex(aChild);           // linear search

        if (index == -1) {
            throw new IllegalArgumentException("node is not a child");
        }

        if (index < getChildCount() - 1) {
            return getChildAt(index + 1);
        } else {
            return null;
        }
    }


    