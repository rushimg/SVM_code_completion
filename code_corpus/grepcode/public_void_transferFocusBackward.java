
    public void transferFocusBackward() {
        transferFocusBackward(false);
    }

    boolean transferFocusBackward(boolean clearOnFailure) {
        Container rootAncestor = getTraversalRoot();
        Component comp = this;
        while (rootAncestor != null &&
               !(rootAncestor.isShowing() && rootAncestor.canBeFocusOwner()))
        {
            comp = rootAncestor;
            rootAncestor = comp.getFocusCycleRootAncestor();
        }
        boolean res = false;
        if (rootAncestor != null) {
            FocusTraversalPolicy policy = rootAncestor.getFocusTraversalPolicy();
            Component toFocus = policy.getComponentBefore(rootAncestor, comp);
            if (toFocus == null) {
                toFocus = policy.getDefaultComponent(rootAncestor);
            }
            if (toFocus != null) {
                res = toFocus.requestFocusInWindow(CausedFocusEvent.Cause.TRAVERSAL_BACKWARD);
            }
        }
        if (!res) {
            if (focusLog.isLoggable(PlatformLogger.FINER)) {
                focusLog.finer("clear global focus owner");
            }
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        }
        if (focusLog.isLoggable(PlatformLogger.FINER)) {
            focusLog.finer("returning result: " + res);
        }
        return res;
    }

    