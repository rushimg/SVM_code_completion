
    public void setComponentOrientation(ComponentOrientation o) {
        ComponentOrientation oldValue = componentOrientation;
        componentOrientation = o;

        // This is a bound property, so report the change to
        // any registered listeners.  (Cheap if there are none.)
        firePropertyChange("componentOrientation", oldValue, o);

        // This could change the preferred size of the Component.
        invalidateIfValid();
    }

    