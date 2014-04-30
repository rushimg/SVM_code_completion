
    public void removeHierarchyListener(HierarchyListener l) {
        if (l == null) {
            return;
        }
        boolean notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierarchyListener != null &&
                 (eventMask & AWTEvent.HIERARCHY_EVENT_MASK) == 0);
            hierarchyListener =
                AWTEventMulticaster.remove(hierarchyListener, l);
            notifyAncestors = (notifyAncestors && hierarchyListener == null);
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                adjustListeningChildrenOnParent(AWTEvent.HIERARCHY_EVENT_MASK,
                                                -1);
            }
        }
    }

    