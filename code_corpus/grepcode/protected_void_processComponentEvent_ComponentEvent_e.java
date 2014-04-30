
    protected void processComponentEvent(ComponentEvent e) {
        ComponentListener listener = componentListener;
        if (listener != null) {
            int id = e.getID();
            switch(id) {
              case ComponentEvent.COMPONENT_RESIZED:
                  listener.componentResized(e);
                  break;
              case ComponentEvent.COMPONENT_MOVED:
                  listener.componentMoved(e);
                  break;
              case ComponentEvent.COMPONENT_SHOWN:
                  listener.componentShown(e);
                  break;
              case ComponentEvent.COMPONENT_HIDDEN:
                  listener.componentHidden(e);
                  break;
            }
        }
    }

    