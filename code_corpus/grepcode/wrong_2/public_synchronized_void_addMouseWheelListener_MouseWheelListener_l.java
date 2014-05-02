
    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticaster.add(mouseWheelListener,l);
        newEventsOnly = true;

        // if this is a lightweight component, enable mouse events
        // in the native container.
        if (peer instanceof LightweightPeer) {
            parent.proxyEnableEvents(AWTEvent.MOUSE_WHEEL_EVENT_MASK);
        }
    }

    