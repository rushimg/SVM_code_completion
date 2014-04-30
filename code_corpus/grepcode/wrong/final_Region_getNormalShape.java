
    final Region getNormalShape() {
        checkTreeLock();
        //XXX: we may take into account a user-specified shape for this component
        Point compAbsolute = getLocationOnWindow();
        return
            Region.getInstanceXYWH(
                    compAbsolute.x,
                    compAbsolute.y,
                    getWidth(),
                    getHeight()
            );
    }

    