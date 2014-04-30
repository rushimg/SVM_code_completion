
    public synchronized void addKeyListener(KeyListener l) {
        if (l == null) {
            return;
        }
        keyListener = AWTEventMulticaster.add(keyListener, l);
        newEventsOnly = true;

        // if this is a lightweight component, enable key events
        // in the native container.
        if (peer instanceof LightweightPeer) {
            parent.proxyEnableEvents(AWTEvent.KEY_EVENT_MASK);
        }
    }

    