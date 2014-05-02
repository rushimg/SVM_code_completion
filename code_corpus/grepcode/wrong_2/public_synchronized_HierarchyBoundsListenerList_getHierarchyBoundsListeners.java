
    public synchronized HierarchyBoundsListener[] getHierarchyBoundsListeners() {
        return (HierarchyBoundsListener[])
            (getListeners(HierarchyBoundsListener.class));
    }

    /*
     * Should only be called while holding the tree lock.
     * It's added only for overriding in java.awt.Window
     * because parent in Window is owner.
     