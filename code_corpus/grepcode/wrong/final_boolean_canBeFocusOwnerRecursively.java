
    final boolean canBeFocusOwnerRecursively() {
        // - it is enabled, visible, focusable
        if (!canBeFocusOwner()) {
            return false;
        }

        // - it's parents are all enabled and showing
        synchronized(getTreeLock()) {
            if (parent != null) {
                return parent.canContainFocusOwner(this);
            }
        }
        return true;
    }

    