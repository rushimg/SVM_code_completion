
    public boolean isFocusCycleRoot(Container container) {
        Container rootAncestor = getFocusCycleRootAncestor();
        return (rootAncestor == container);
    }

    Container getTraversalRoot() {
        return getFocusCycleRootAncestor();
    }

    