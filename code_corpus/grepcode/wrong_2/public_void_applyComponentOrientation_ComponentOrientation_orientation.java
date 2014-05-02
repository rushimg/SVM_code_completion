
    public void applyComponentOrientation(ComponentOrientation orientation) {
        if (orientation == null) {
            throw new NullPointerException();
        }
        setComponentOrientation(orientation);
    }

    final boolean canBeFocusOwner() {
        // It is enabled, visible, focusable.
        if (isEnabled() && isDisplayable() && isVisible() && isFocusable()) {
            return true;
        }
        return false;
    }

    