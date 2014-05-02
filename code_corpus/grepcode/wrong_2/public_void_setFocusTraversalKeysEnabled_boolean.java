
    public void setFocusTraversalKeysEnabled(boolean
                                             focusTraversalKeysEnabled) {
        boolean oldFocusTraversalKeysEnabled;
        synchronized (this) {
            oldFocusTraversalKeysEnabled = this.focusTraversalKeysEnabled;
            this.focusTraversalKeysEnabled = focusTraversalKeysEnabled;
        }
        firePropertyChange("focusTraversalKeysEnabled",
                           oldFocusTraversalKeysEnabled,
                           focusTraversalKeysEnabled);
    }

    