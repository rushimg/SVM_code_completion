
    public void addHierarchyBoundsListener(HierarchyBoundsListener l) {
        if (l == null) {
            return;
        }
        boolean notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierarchyBoundsListener == null &&
                 (eventMask & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            hierarchyBoundsListener =
                AWTEventMulticaster.add(hierarchyBoundsListener, l);
            notifyAncestors = (notifyAncestors &&
                               hierarchyBoundsListener != null);
            newEventsOnly = true;
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                adjustListeningChildrenOnParent(
                                                AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK, 1);
            }
        }
    }

    