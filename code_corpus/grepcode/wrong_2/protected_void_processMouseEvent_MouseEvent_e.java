
    protected void processMouseEvent(MouseEvent e) {
        MouseListener listener = mouseListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case MouseEvent.MOUSE_PRESSED:
                  listener.mousePressed(e);
                  break;
              case MouseEvent.MOUSE_RELEASED:
                  listener.mouseReleased(e);
                  break;
              case MouseEvent.MOUSE_CLICKED:
                  listener.mouseClicked(e);
                  break;
              case MouseEvent.MOUSE_EXITED:
                  listener.mouseExited(e);
                  break;
              case MouseEvent.MOUSE_ENTERED:
                  listener.mouseEntered(e);
                  break;
            }
        }
    }

    