
    public synchronized void addFocusListener(FocusListener l) {
        if (l == null) {
            return;
        }
        focusListener = AWTEventMulticaster.add(focusListener, l);
        newEventsOnly = true;

        // if this is a lightweight component, enable focus events
        // in the native container.
        if (peer instanceof LightweightPeer) {
            parent.proxyEnableEvents(AWTEvent.FOCUS_EVENT_MASK);
        }
    }

    