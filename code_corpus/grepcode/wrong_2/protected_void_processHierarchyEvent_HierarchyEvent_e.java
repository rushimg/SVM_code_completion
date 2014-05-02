
    protected void processHierarchyEvent(HierarchyEvent e) {
        HierarchyListener listener = hierarchyListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              case HierarchyEvent.HIERARCHY_CHANGED:
                  listener.hierarchyChanged(e);
                  break;
            }
        }
    }

    