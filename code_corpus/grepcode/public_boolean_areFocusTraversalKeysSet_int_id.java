
    public boolean areFocusTraversalKeysSet(int id) {
        if (id < 0 || id >= KeyboardFocusManager.TRAVERSAL_KEY_LENGTH - 1) {
            throw new IllegalArgumentException("invalid focus traversal key identifier");
        }

        return (focusTraversalKeys != null && focusTraversalKeys[id] != null);
    }

    