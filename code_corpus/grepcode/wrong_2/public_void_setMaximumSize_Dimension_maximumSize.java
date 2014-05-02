
    public void setMaximumSize(Dimension maximumSize) {
        // If the maximum size was set, use it as the old value, otherwise
        // use null to indicate we didn't previously have a set maximum
        // size.
        Dimension old;
        if (maxSizeSet) {
            old = this.maxSize;
        }
        else {
            old = null;
        }
        this.maxSize = maximumSize;
        maxSizeSet = (maximumSize != null);
        firePropertyChange("maximumSize", old, maximumSize);
    }

    