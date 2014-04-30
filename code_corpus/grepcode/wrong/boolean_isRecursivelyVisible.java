
    boolean isRecursivelyVisible() {
        return visible && (parent == null || parent.isRecursivelyVisible());
    }

    