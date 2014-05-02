
    public void add(MutableTreeNode newChild) {
        if(newChild != null && newChild.getParent() == this)
            insert(newChild, getChildCount() - 1);
        else
            insert(newChild, getChildCount());
    }



    //
    //  Tree Queries
    //

    