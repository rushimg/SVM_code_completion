
    public boolean isFocusOwner() {
        return hasFocus();
    }

    /*
     * Used to disallow auto-focus-transfer on disposal of the focus owner
     * in the process of disposing its parent container.
     