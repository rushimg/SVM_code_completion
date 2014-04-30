
    void applyCompoundShape(Region shape) {
        checkTreeLock();

        if (!areBoundsValid()) {
            if (mixingLog.isLoggable(PlatformLogger.FINE)) {
                mixingLog.fine("this = " + this + "; areBoundsValid = " + areBoundsValid());
            }
            return;
        }

        if (!isLightweight()) {
            ComponentPeer peer = getPeer();
            if (peer != null) {
                // The Region class has some optimizations. That's why
                // we should manually check whether it's empty and
                // substitute the object ourselves. Otherwise we end up
                // with some incorrect Region object with loX being
                // greater than the hiX for instance.
                if (shape.isEmpty()) {
                    shape = Region.EMPTY_REGION;
                }


                // Note: the shape is not really copied/cloned. We create
                // the Region object ourselves, so there's no any possibility
                // to modify the object outside of the mixing code.
                // Nullifying compoundShape means that the component has normal shape
                // (or has no shape at all).
                if (shape.equals(getNormalShape())) {
                    if (this.compoundShape == null) {
                        return;
                    }
                    this.compoundShape = null;
                    peer.applyShape(null);
                } else {
                    if (shape.equals(getAppliedShape())) {
                        return;
                    }
                    this.compoundShape = shape;
                    Point compAbsolute = getLocationOnWindow();
                    if (mixingLog.isLoggable(PlatformLogger.FINER)) {
                        mixingLog.fine("this = " + this +
                                "; compAbsolute=" + compAbsolute + "; shape=" + shape);
                    }
                    peer.applyShape(shape.getTranslatedRegion(-compAbsolute.x, -compAbsolute.y));
                }
            }
        }
    }

    