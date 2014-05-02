
    protected void processMouseMotionEvent(MouseEvent e) {
        MouseMotionListener listener = mouseMotionListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case MouseEvent.MOUSE_MOVED:
                  listener.mouseMoved(e);
                  break;
              case MouseEvent.MOUSE_DRAGGED:
                  listener.mouseDragged(e);
                  break;
            }
        }
    }

    