
    public void removeFromParent() {
        MutableTreeNode parent = (MutableTreeNode)getParent();
        if (parent != null) {
            parent.remove(this);
        }
    }

    