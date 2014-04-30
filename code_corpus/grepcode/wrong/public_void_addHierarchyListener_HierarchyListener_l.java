
    public void addHierarchyListener(HierarchyListener l) {
        if (l == null) {
            return;
        }
        boolean notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierarchyListener == null &&
                 (eventMask & AWTEvent.HIERARCHY_EVENT_MASK) == 0);
            hierarchyListener = AWTEventMulticaster.add(hierarchyListener, l);
            notifyAncestors = (notifyAncestors && hierarchyListener != null);
            newEventsOnly = true;
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                adjustListeningChildrenOnParent(AWTEvent.HIERARCHY_EVENT_MASK,
                                                1);
            }
        }
    }

    