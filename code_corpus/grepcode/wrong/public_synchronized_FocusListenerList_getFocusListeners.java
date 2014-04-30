
    public synchronized FocusListener[] getFocusListeners() {
        return (FocusListener[]) (getListeners(FocusListener.class));
    }

    