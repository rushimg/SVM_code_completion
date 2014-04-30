
    public void setLocale(Locale l) {
        Locale oldValue = locale;
        locale = l;

        // This is a bound property, so report the change to
        // any registered listeners.  (Cheap if there are none.)
        firePropertyChange("locale", oldValue, l);

        // This could change the preferred size of the Component.
        invalidateIfValid();
    }

    