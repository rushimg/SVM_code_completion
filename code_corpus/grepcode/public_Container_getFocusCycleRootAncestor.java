
    public Container getFocusCycleRootAncestor() {
        Container rootAncestor = this.parent;
        while (rootAncestor != null && !rootAncestor.isFocusCycleRoot()) {
            rootAncestor = rootAncestor.parent;
        }
        return rootAncestor;
    }

    