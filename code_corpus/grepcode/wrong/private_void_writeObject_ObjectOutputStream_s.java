
    private void writeObject(ObjectOutputStream s)
      throws IOException
    {
        doSwingSerialization();

        s.defaultWriteObject();

        AWTEventMulticaster.save(s, componentListenerK, componentListener);
        AWTEventMulticaster.save(s, focusListenerK, focusListener);
        AWTEventMulticaster.save(s, keyListenerK, keyListener);
        AWTEventMulticaster.save(s, mouseListenerK, mouseListener);
        AWTEventMulticaster.save(s, mouseMotionListenerK, mouseMotionListener);
        AWTEventMulticaster.save(s, inputMethodListenerK, inputMethodListener);

        s.writeObject(null);
        s.writeObject(componentOrientation);

        AWTEventMulticaster.save(s, hierarchyListenerK, hierarchyListener);
        AWTEventMulticaster.save(s, hierarchyBoundsListenerK,
                                 hierarchyBoundsListener);
        s.writeObject(null);

        AWTEventMulticaster.save(s, mouseWheelListenerK, mouseWheelListener);
        s.writeObject(null);

    }

    