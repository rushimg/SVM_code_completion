
    protected void processEvent(AWTEvent e) {
        if (e instanceof FocusEvent) {
            processFocusEvent((FocusEvent)e);

        } else if (e instanceof MouseEvent) {
            switch(e.getID()) {
              case MouseEvent.MOUSE_PRESSED:
              case MouseEvent.MOUSE_RELEASED:
              case MouseEvent.MOUSE_CLICKED:
              case MouseEvent.MOUSE_ENTERED:
              case MouseEvent.MOUSE_EXITED:
                  processMouseEvent((MouseEvent)e);
                  break;
              case MouseEvent.MOUSE_MOVED:
              case MouseEvent.MOUSE_DRAGGED:
                  processMouseMotionEvent((MouseEvent)e);
                  break;
              case MouseEvent.MOUSE_WHEEL:
                  processMouseWheelEvent((MouseWheelEvent)e);
                  break;
            }

        } else if (e instanceof KeyEvent) {
            processKeyEvent((KeyEvent)e);

        } else if (e instanceof ComponentEvent) {
            processComponentEvent((ComponentEvent)e);
        } else if (e instanceof InputMethodEvent) {
            processInputMethodEvent((InputMethodEvent)e);
        } else if (e instanceof HierarchyEvent) {
            switch (e.getID()) {
              case HierarchyEvent.HIERARCHY_CHANGED:
                  processHierarchyEvent((HierarchyEvent)e);
                  break;
              case HierarchyEvent.ANCESTOR_MOVED:
              case HierarchyEvent.ANCESTOR_RESIZED:
                  processHierarchyBoundsEvent((HierarchyEvent)e);
                  break;
            }
        }
    }

    