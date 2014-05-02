
    protected boolean requestFocus(boolean temporary) {
        return requestFocusHelper(temporary, true);
    }

    boolean requestFocus(boolean temporary, CausedFocusEvent.Cause cause) {
        return requestFocusHelper(temporary, true, cause);
    }
    