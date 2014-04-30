
    public void setMinimumSize(Dimension minimumSize) {
        Dimension old;
        // If the minimum size was set, use it as the old value, otherwise
        // use null to indicate we didn't previously have a set minimum
        // size.
        if (minSizeSet) {
            old = this.minSize;
        }
        else {
            old = null;
        }
        this.minSize = minimumSize;
        minSizeSet = (minimumSize != null);
        firePropertyChange("minimumSize", old, minimumSize);
    }

    