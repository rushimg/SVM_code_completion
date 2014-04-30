
    public void firePropertyChange(String propertyName, double oldValue, double newValue) {
        if (changeSupport == null || oldValue == newValue) {
            return;
        }
        firePropertyChange(propertyName, Double.valueOf(oldValue), Double.valueOf(newValue));
    }


    // Serialization support.

    