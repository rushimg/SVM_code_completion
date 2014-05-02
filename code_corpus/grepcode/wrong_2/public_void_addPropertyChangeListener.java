
    public void addPropertyChangeListener(
                                                       String propertyName,
                                                       PropertyChangeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null) {
                return;
            }
            if (changeSupport == null) {
                changeSupport = new PropertyChangeSupport(this);
            }
            changeSupport.addPropertyChangeListener(propertyName, listener);
        }
    }

    