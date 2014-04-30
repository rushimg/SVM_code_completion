
    protected boolean requestFocusInWindow(boolean temporary) {
        return requestFocusHelper(temporary, false);
    }

    boolean requestFocusInWindow(boolean temporary, CausedFocusEvent.Cause cause) {
        return requestFocusHelper(temporary, false, cause);
    }

    final boolean requestFocusHelper(boolean temporary,
                                     boolean focusedWindowChangeAllowed) {
        return requestFocusHelper(temporary, focusedWindowChangeAllowed, CausedFocusEvent.Cause.UNKNOWN);
    }

    final boolean requestFocusHelper(boolean temporary,
                                     boolean focusedWindowChangeAllowed,
                                     CausedFocusEvent.Cause cause)
    {
        if (!isRequestFocusAccepted(temporary, focusedWindowChangeAllowed, cause)) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("requestFocus is not accepted");
            }
            return false;
        }

        // Update most-recent map
        KeyboardFocusManager.setMostRecentFocusOwner(this);

        Component window = this;
        while ( (window != null) && !(window instanceof Window)) {
            if (!window.isVisible()) {
                if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                    focusLog.finest("component is recurively invisible");
                }
                return false;
            }
            window = window.parent;
        }

        ComponentPeer peer = this.peer;
        Component heavyweight = (peer instanceof LightweightPeer)
            ? getNativeContainer() : this;
        if (heavyweight == null || !heavyweight.isVisible()) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Component is not a part of visible hierarchy");
            }
            return false;
        }
        peer = heavyweight.peer;
        if (peer == null) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Peer is null");
            }
            return false;
        }

        // Focus this Component
        long time = EventQueue.getMostRecentEventTime();
        boolean success = peer.requestFocus
            (this, temporary, focusedWindowChangeAllowed, time, cause);
        if (!success) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager
                (appContext).dequeueKeyEvents(time, this);
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Peer request failed");
            }
        } else {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Pass for " + this);
            }
        }
        return success;
    }

    private boolean isRequestFocusAccepted(boolean temporary,
                                           boolean focusedWindowChangeAllowed,
                                           CausedFocusEvent.Cause cause)
    {
        if (!isFocusable() || !isVisible()) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Not focusable or not visible");
            }
            return false;
        }

        ComponentPeer peer = this.peer;
        if (peer == null) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("peer is null");
            }
            return false;
        }

        Window window = getContainingWindow();
        if (window == null || !((Window)window).isFocusableWindow()) {
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("Component doesn't have toplevel");
            }
            return false;
        }

        // We have passed all regular checks for focus request,
        // now let's call RequestFocusController and see what it says.
        Component focusOwner = KeyboardFocusManager.getMostRecentFocusOwner(window);
        if (focusOwner == null) {
            // sometimes most recent focus owner may be null, but focus owner is not
            // e.g. we reset most recent focus owner if user removes focus owner
            focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            if (focusOwner != null && focusOwner.getContainingWindow() != window) {
                focusOwner = null;
            }
        }

        if (focusOwner == this || focusOwner == null) {
            // Controller is supposed to verify focus transfers and for this it
            // should know both from and to components.  And it shouldn't verify
            // transfers from when these components are equal.
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("focus owner is null or this");
            }
            return true;
        }

        if (CausedFocusEvent.Cause.ACTIVATION == cause) {
            // we shouldn't call RequestFocusController in case we are
            // in activation.  We do request focus on component which
            // has got temporary focus lost and then on component which is
            // most recent focus owner.  But most recent focus owner can be
            // changed by requestFocsuXXX() call only, so this transfer has
            // been already approved.
            if (focusLog.isLoggable(PlatformLogger.FINEST)) {
                focusLog.finest("cause is activation");
            }
            return true;
        }

        boolean ret = Component.requestFocusController.acceptRequestFocus(focusOwner,
                                                                          this,
                                                                          temporary,
                                                                          focusedWindowChangeAllowed,
                                                                          cause);
        if (focusLog.isLoggable(PlatformLogger.FINEST)) {
            focusLog.finest("RequestFocusController returns {0}", ret);
        }

        return ret;
    }

    private static RequestFocusController requestFocusController = new DummyRequestFocusController();

    // Swing access this method through reflection to implement InputVerifier's functionality.
    // Perhaps, we should make this method public (later ;)
    private static class DummyRequestFocusController implements RequestFocusController {
        public boolean acceptRequestFocus(Component from, Component to,
                                          boolean temporary, boolean focusedWindowChangeAllowed,
                                          CausedFocusEvent.Cause cause)
        {
            return true;
        }
    };

    synchronized static void setRequestFocusController(RequestFocusController requestController)
    {
        if (requestController == null) {
            requestFocusController = new DummyRequestFocusController();
        } else {
            requestFocusController = requestController;
        }
    }

    