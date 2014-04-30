
    protected void processFocusEvent(FocusEvent e) {
        FocusListener listener = focusListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case FocusEvent.FOCUS_GAINED:
                  listener.focusGained(e);
                  break;
              case FocusEvent.FOCUS_LOST:
                  listener.focusLost(e);
                  break;
            }
        }
    }

    