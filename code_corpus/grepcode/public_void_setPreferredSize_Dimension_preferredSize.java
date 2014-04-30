
    public void setPreferredSize(Dimension preferredSize) {
        Dimension old;
        // If the preferred size was set, use it as the old value, otherwise
        // use null to indicate we didn't previously have a set preferred
        // size.
        if (prefSizeSet) {
            old = this.prefSize;
        }
        else {
            old = null;
        }
        this.prefSize = preferredSize;
        prefSizeSet = (preferredSize != null);
        firePropertyChange("preferredSize", old, preferredSize);
    }


    