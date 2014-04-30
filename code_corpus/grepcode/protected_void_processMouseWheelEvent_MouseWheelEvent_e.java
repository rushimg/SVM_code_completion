
    protected void processMouseWheelEvent(MouseWheelEvent e) {
        MouseWheelListener listener = mouseWheelListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case MouseEvent.MOUSE_WHEEL:
                  listener.mouseWheelMoved(e);
                  break;
            }
        }
    }

    boolean postsOldMouseEvents() {
        return false;
    }

    