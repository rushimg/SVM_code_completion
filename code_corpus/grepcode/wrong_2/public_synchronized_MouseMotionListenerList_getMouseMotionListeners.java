
    public synchronized MouseMotionListener[] getMouseMotionListeners() {
        return (MouseMotionListener[]) (getListeners(MouseMotionListener.class));
    }

    