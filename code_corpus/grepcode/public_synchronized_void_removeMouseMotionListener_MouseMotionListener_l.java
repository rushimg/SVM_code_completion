
    public synchronized void removeMouseMotionListener(MouseMotionListener l) {
        if (l == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticaster.remove(mouseMotionListener, l);
    }

    