
    public final Object getTreeLock() {
        return LOCK;
    }

    final void checkTreeLock() {
        if (!Thread.holdsLock(getTreeLock())) {
            throw new IllegalStateException("This function should be called while holding treeLock");
        }
    }

    