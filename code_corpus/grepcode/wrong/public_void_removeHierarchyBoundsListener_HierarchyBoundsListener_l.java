
    public void removeHierarchyBoundsListener(HierarchyBoundsListener l) {
        if (l == null) {
            return;
        }
        boolean notifyAncestors;
        synchronized (this) {
            notifyAncestors =
                (hierarchyBoundsListener != null &&
                 (eventMask & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) == 0);
            hierarchyBoundsListener =
                AWTEventMulticaster.remove(hierarchyBoundsListener, l);
            notifyAncestors = (notifyAncestors &&
                               hierarchyBoundsListener == null);
        }
        if (notifyAncestors) {
            synchronized (getTreeLock()) {
                adjustListeningChildrenOnParent(
                                                AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK, -1);
            }
        }
    }

    // Should only be called while holding the tree lock
    int numListening(long mask) {
        // One mask or the other, but not neither or both.
        if (eventLog.isLoggable(PlatformLogger.FINE)) {
            if ((mask != AWTEvent.HIERARCHY_EVENT_MASK) &&
                (mask != AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK))
            {
                eventLog.fine("Assertion failed");
            }
        }
        if ((mask == AWTEvent.HIERARCHY_EVENT_MASK &&
             (hierarchyListener != null ||
              (eventMask & AWTEvent.HIERARCHY_EVENT_MASK) != 0)) ||
            (mask == AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK &&
             (hierarchyBoundsListener != null ||
              (eventMask & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0))) {
            return 1;
        } else {
            return 0;
        }
    }

    // Should only be called while holding tree lock
    int countHierarchyMembers() {
        return 1;
    }
    // Should only be called while holding the tree lock
    int createHierarchyEvents(int id, Component changed,
                              Container changedParent, long changeFlags,
                              boolean enabledOnToolkit) {
        switch (id) {
          case HierarchyEvent.HIERARCHY_CHANGED:
              if (hierarchyListener != null ||
                  (eventMask & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                  enabledOnToolkit) {
                  HierarchyEvent e = new HierarchyEvent(this, id, changed,
                                                        changedParent,
                                                        changeFlags);
                  dispatchEvent(e);
                  return 1;
              }
              break;
          case HierarchyEvent.ANCESTOR_MOVED:
          case HierarchyEvent.ANCESTOR_RESIZED:
              if (eventLog.isLoggable(PlatformLogger.FINE)) {
                  if (changeFlags != 0) {
                      eventLog.fine("Assertion (changeFlags == 0) failed");
                  }
              }
              if (hierarchyBoundsListener != null ||
                  (eventMask & AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK) != 0 ||
                  enabledOnToolkit) {
                  HierarchyEvent e = new HierarchyEvent(this, id, changed,
                                                        changedParent);
                  dispatchEvent(e);
                  return 1;
              }
              break;
          default:
              // assert false
              if (eventLog.isLoggable(PlatformLogger.FINE)) {
                  eventLog.fine("This code must never be reached");
              }
              break;
        }
        return 0;
    }

    