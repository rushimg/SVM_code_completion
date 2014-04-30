
    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ComponentListener.class) {
            l = componentListener;
        } else if (listenerType == FocusListener.class) {
            l = focusListener;
        } else if (listenerType == HierarchyListener.class) {
            l = hierarchyListener;
        } else if (listenerType == HierarchyBoundsListener.class) {
            l = hierarchyBoundsListener;
        } else if (listenerType == KeyListener.class) {
            l = keyListener;
        } else if (listenerType == MouseListener.class) {
            l = mouseListener;
        } else if (listenerType == MouseMotionListener.class) {
            l = mouseMotionListener;
        } else if (listenerType == MouseWheelListener.class) {
            l = mouseWheelListener;
        } else if (listenerType == InputMethodListener.class) {
            l = inputMethodListener;
        } else if (listenerType == PropertyChangeListener.class) {
            return (T[])getPropertyChangeListeners();
        }
        return AWTEventMulticaster.getListeners(l, listenerType);
    }

    