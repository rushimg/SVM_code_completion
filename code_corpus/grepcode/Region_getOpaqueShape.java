
    Region getOpaqueShape() {
        checkTreeLock();
        if (mixingCutoutRegion != null) {
            return mixingCutoutRegion;
        } else {
            return getNormalShape();
        }
    }

    final int getSiblingIndexAbove() {
        checkTreeLock();
        Container parent = getContainer();
        if (parent == null) {
            return -1;
        }

        int nextAbove = parent.getComponentZOrder(this) - 1;

        return nextAbove < 0 ? -1 : nextAbove;
    }

    final ComponentPeer getHWPeerAboveMe() {
        checkTreeLock();

        Container cont = getContainer();
        int indexAbove = getSiblingIndexAbove();

        while (cont != null) {
            for (int i = indexAbove; i > -1; i--) {
                Component comp = cont.getComponent(i);
                if (comp != null && comp.isDisplayable() && !comp.isLightweight()) {
                    return comp.getPeer();
                }
            }
            // traversing the hierarchy up to the closest HW container;
            // further traversing may return a component that is not actually
            // a native sibling of this component and this kind of z-order
            // request may not be allowed by the underlying system (6852051).
            if (!cont.isLightweight()) {
                break;
            }

            indexAbove = cont.getSiblingIndexAbove();
            cont = cont.getContainer();
        }

        return null;
    }

    final int getSiblingIndexBelow() {
        checkTreeLock();
        Container parent = getContainer();
        if (parent == null) {
            return -1;
        }

        int nextBelow = parent.getComponentZOrder(this) + 1;

        return nextBelow >= parent.getComponentCount() ? -1 : nextBelow;
    }

    final boolean isNonOpaqueForMixing() {
        return mixingCutoutRegion != null &&
            mixingCutoutRegion.isEmpty();
    }

    private Region calculateCurrentShape() {
        checkTreeLock();
        Region s = getNormalShape();

        if (mixingLog.isLoggable(PlatformLogger.FINE)) {
            mixingLog.fine("this = " + this + "; normalShape=" + s);
        }

        if (getContainer() != null) {
            Component comp = this;
            Container cont = comp.getContainer();

            while (cont != null) {
                for (int index = comp.getSiblingIndexAbove(); index != -1; --index) {
                    /* It is assumed that:
                     *
                     *    getComponent(getContainer().getComponentZOrder(comp)) == comp
                     *
                     * The assumption has been made according to the current
                     * implementation of the Container class.
                     