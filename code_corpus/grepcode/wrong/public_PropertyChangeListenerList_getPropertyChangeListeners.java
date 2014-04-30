
    public PropertyChangeListener[] getPropertyChangeListeners(
                                                                            String propertyName) {
        synchronized (getObjectLock()) {
            if (changeSupport == null) {
                return new PropertyChangeListener[0];
            }
            return changeSupport.getPropertyChangeListeners(propertyName);
        }
    }

    