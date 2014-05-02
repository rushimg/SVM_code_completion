
    public synchronized void removeFocusListener(FocusListener l) {
        if (l == null) {
            return;
        }
        focusListener = AWTEventMulticaster.remove(focusListener, l);
    }

    