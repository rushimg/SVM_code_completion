
    public synchronized void addComponentListener(ComponentListener l) {
        if (l == null) {
            return;
        }
        componentListener = AWTEventMulticaster.add(componentListener, l);
        newEventsOnly = true;
    }

    