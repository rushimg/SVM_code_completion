
    public void firePropertyChange(String propertyName, long oldValue, long newValue) {
        if (changeSupport == null || oldValue == newValue) {
            return;
        }
        firePropertyChange(propertyName, Long.valueOf(oldValue), Long.valueOf(newValue));
    }

    