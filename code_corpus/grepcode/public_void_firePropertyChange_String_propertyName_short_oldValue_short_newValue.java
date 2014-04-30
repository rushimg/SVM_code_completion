
    public void firePropertyChange(String propertyName, short oldValue, short newValue) {
        if (changeSupport == null || oldValue == newValue) {
            return;
        }
        firePropertyChange(propertyName, Short.valueOf(oldValue), Short.valueOf(newValue));
    }


    