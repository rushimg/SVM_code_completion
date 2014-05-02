
    public final void dispatchEvent(AWTEvent e) {
        dispatchEventImpl(e);
    }

    void dispatchEventImpl(AWTEvent e) {
        int id = e.getID();

        // Check that this component belongs to this app-context
        AppContext compContext = appContext;
        if (compContext != null && !compContext.equals(AppContext.getAppContext())) {
            if (eventLog.isLoggable(PlatformLogger.FINE)) {
                eventLog.fine("Event " + e + " is being dispatched on the wrong AppContext");
            }
        }

        if (eventLog.isLoggable(PlatformLogger.FINEST)) {
            eventLog.finest("{0}", e);
        }

        /*
         * 0. Set timestamp and modifiers of current event.
         