
    public synchronized void addMouseListener(MouseListener l) {
        if (l == null) {
            return;
        }
        mouseListener = AWTEventMulticaster.add(mouseListener,l);
        newEventsOnly = true;

        // if this is a lightweight component, enable mouse events
        // in the native container.
        if (peer instanceof LightweightPeer) {
            parent.proxyEnableEvents(AWTEvent.MOUSE_EVENT_MASK);
        }
    }

    