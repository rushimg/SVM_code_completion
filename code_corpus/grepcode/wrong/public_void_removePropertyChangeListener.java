
    public void removePropertyChangeListener(
                                                          String propertyName,
                                                          PropertyChangeListener listener) {
        synchronized (getObjectLock()) {
            if (listener == null || changeSupport == null) {
                return;
            }
            changeSupport.removePropertyChangeListener(propertyName, listener);
        }
    }

    