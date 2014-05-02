
    public TreeNode getChildBefore(TreeNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        int index = getIndex(aChild);           // linear search

        if (index == -1) {
            throw new IllegalArgumentException("argument is not a child");
        }

        if (index > 0) {
            return getChildAt(index - 1);
        } else {
            return null;
        }
    }


    //
    //  Sibling Queries
    //


    