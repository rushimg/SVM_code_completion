
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticaster.add(mouseMotionListener,l);
        newEventsOnly = true;

        // if this is a lightweight component, enable mouse events
        // in the native container.
        if (peer instanceof LightweightPeer) {
            parent.proxyEnableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
    }

    