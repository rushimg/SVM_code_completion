
    protected void processHierarchyBoundsEvent(HierarchyEvent e) {
        HierarchyBoundsListener listener = hierarchyBoundsListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              case HierarchyEvent.ANCESTOR_MOVED:
                  listener.ancestorMoved(e);
                  break;
              case HierarchyEvent.ANCESTOR_RESIZED:
                  listener.ancestorResized(e);
                  break;
            }
        }
    }

    