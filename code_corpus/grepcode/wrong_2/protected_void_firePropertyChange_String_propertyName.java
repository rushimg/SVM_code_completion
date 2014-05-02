
    protected void firePropertyChange(String propertyName,
                                      int oldValue, int newValue) {
        PropertyChangeSupport changeSupport = this.changeSupport;
        if (changeSupport == null || oldValue == newValue) {
            return;
        }
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    