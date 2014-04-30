
    protected void processKeyEvent(KeyEvent e) {
        KeyListener listener = keyListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case KeyEvent.KEY_TYPED:
                  listener.keyTyped(e);
                  break;
              case KeyEvent.KEY_PRESSED:
                  listener.keyPressed(e);
                  break;
              case KeyEvent.KEY_RELEASED:
                  listener.keyReleased(e);
                  break;
            }
        }
    }

    