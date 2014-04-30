
    public Container getParent() {
        return getParent_NoClientCode();
    }

    // NOTE: This method may be called by privileged threads.
    //       This functionality is implemented in a package-private method
    //       to insure that it cannot be overridden by client subclasses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    final Container getParent_NoClientCode() {
        return parent;
    }

    // This method is overriden in the Window class to return null,
    //    because the parent field of the Window object contains
    //    the owner of the window, not its parent.
    Container getContainer() {
        return getParent();
    }

    