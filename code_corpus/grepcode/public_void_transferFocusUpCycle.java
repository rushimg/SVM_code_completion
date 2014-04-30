
    public void transferFocusUpCycle() {
        Container rootAncestor;
        for (rootAncestor = getFocusCycleRootAncestor();
             rootAncestor != null && !(rootAncestor.isShowing() &&
                                       rootAncestor.isFocusable() &&
                                       rootAncestor.isEnabled());
             rootAncestor = rootAncestor.getFocusCycleRootAncestor()) {
        }

        if (rootAncestor != null) {
            Container rootAncestorRootAncestor =
                rootAncestor.getFocusCycleRootAncestor();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().
                setGlobalCurrentFocusCycleRoot(
                                               (rootAncestorRootAncestor != null)
                                               ? rootAncestorRootAncestor
                                               : rootAncestor);
            rootAncestor.requestFocus(CausedFocusEvent.Cause.TRAVERSAL_UP);
        } else {
            Window window = getContainingWindow();

            if (window != null) {
                Component toFocus = window.getFocusTraversalPolicy().
                    getDefaultComponent(window);
                if (toFocus != null) {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().
                        setGlobalCurrentFocusCycleRoot(window);
                    toFocus.requestFocus(CausedFocusEvent.Cause.TRAVERSAL_UP);
                }
            }
        }
    }

    