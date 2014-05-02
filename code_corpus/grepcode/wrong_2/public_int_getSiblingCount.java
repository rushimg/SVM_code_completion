
    public int getSiblingCount() {
        TreeNode myParent = getParent();

        if (myParent == null) {
            return 1;
        } else {
            return myParent.getChildCount();
        }
    }


    