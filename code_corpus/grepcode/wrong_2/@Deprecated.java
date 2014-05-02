
    @Deprecated
    public void nextFocus() {
        transferFocus(false);
    }

    boolean transferFocus(boolean clearOnFailure) {
        if (focusLog.isLoggable(PlatformLogger.FINER)) {
            focusLog.finer("clearOnFailure = " + clearOnFailure);
        }
        Component toFocus = getNextFocusCandidate();
        boolean res = false;
        if (toFocus != null && !toFocus.isFocusOwner() && toFocus != this) {
            res = toFocus.requestFocusInWindow(CausedFocusEvent.Cause.TRAVERSAL_FORWARD);
        }
        if (clearOnFailure && !res) {
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

    final Component getNextFocusCandidate() {
        Container rootAncestor = getTraversalRoot();
        Component comp = this;
        while (rootAncestor != null &&
               !(rootAncestor.isShowing() && rootAncestor.canBeFocusOwner()))
        {
            comp = rootAncestor;
            rootAncestor = comp.getFocusCycleRootAncestor();
        }
        if (focusLog.isLoggable(PlatformLogger.FINER)) {
            focusLog.finer("comp = " + comp + ", root = " + rootAncestor);
        }
        Component candidate = null;
        if (rootAncestor != null) {
            FocusTraversalPolicy policy = rootAncestor.getFocusTraversalPolicy();
            Component toFocus = policy.getComponentAfter(rootAncestor, comp);
            if (focusLog.isLoggable(PlatformLogger.FINER)) {
                focusLog.finer("component after is " + toFocus);
            }
            if (toFocus == null) {
                toFocus = policy.getDefaultComponent(rootAncestor);
                if (focusLog.isLoggable(PlatformLogger.FINER)) {
                    focusLog.finer("default component is " + toFocus);
                }
            }
            if (toFocus == null) {
                Applet applet = EmbeddedFrame.getAppletIfAncestorOf(this);
                if (applet != null) {
                    toFocus = applet;
                }
            }
            candidate = toFocus;
        }
        if (focusLog.isLoggable(PlatformLogger.FINER)) {
            focusLog.finer("Focus transfer candidate: " + candidate);
        }
        return candidate;
    }

    