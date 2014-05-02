
    public synchronized ComponentListener[] getComponentListeners() {
        return (ComponentListener[]) (getListeners(ComponentListener.class));
    }

    