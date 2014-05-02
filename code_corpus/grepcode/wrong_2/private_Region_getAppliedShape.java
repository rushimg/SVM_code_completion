
    private Region getAppliedShape() {
        checkTreeLock();
        //XXX: if we allow LW components to have a shape, this must be changed
        return (this.compoundShape == null || isLightweight()) ? getNormalShape() : this.compoundShape;
    }

    Point getLocationOnWindow() {
        checkTreeLock();
        Point curLocation = getLocation();

        for (Container parent = getContainer();
                parent != null && !(parent instanceof Window);
                parent = parent.getContainer())
        {
            curLocation.x += parent.getX();
            curLocation.y += parent.getY();
        }

        return curLocation;
    }

    