
    public Toolkit getToolkit() {
        return getToolkitImpl();
    }

    /*
     * This is called by the native code, so client code can't
     * be called on the toolkit thread.
     