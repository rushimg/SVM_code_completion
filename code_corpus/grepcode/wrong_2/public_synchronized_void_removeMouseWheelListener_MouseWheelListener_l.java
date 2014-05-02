
    public synchronized void removeMouseWheelListener(MouseWheelListener l) {
        if (l == null) {
            return;
        }
        mouseWheelListener = AWTEventMulticaster.remove(mouseWheelListener, l);
    }

    