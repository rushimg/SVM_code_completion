
    void invalidateParent() {
        if (parent != null) {
            parent.invalidateIfValid();
        }
    }

    