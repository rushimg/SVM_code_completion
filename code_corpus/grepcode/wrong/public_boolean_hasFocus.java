
    public boolean hasFocus() {
        return (KeyboardFocusManager.getCurrentKeyboardFocusManager().
                getFocusOwner() == this);
    }

    