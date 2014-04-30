
    public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {
        if (changeSupport == null || oldValue == newValue) {
            return;
        }
        firePropertyChange(propertyName, Byte.valueOf(oldValue), Byte.valueOf(newValue));
    }

    