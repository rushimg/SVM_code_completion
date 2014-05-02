
    protected void processInputMethodEvent(InputMethodEvent e) {
        InputMethodListener listener = inputMethodListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
              case InputMethodEvent.INPUT_METHOD_TEXT_CHANGED:
                  listener.inputMethodTextChanged(e);
                  break;
              case InputMethodEvent.CARET_POSITION_CHANGED:
                  listener.caretPositionChanged(e);
                  break;
            }
        }
    }

    