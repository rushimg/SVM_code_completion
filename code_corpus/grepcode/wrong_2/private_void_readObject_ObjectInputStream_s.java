
    private void readObject(ObjectInputStream s)
      throws ClassNotFoundException, IOException
    {
        objectLock = new Object();

        acc = AccessController.getContext();

        s.defaultReadObject();

        appContext = AppContext.getAppContext();
        coalescingEnabled = checkCoalescing();
        if (componentSerializedDataVersion < 4) {
            // These fields are non-transient and rely on default
            // serialization. However, the default values are insufficient,
            // so we need to set them explicitly for object data streams prior
            // to 1.4.
            focusable = true;
            isFocusTraversableOverridden = FOCUS_TRAVERSABLE_UNKNOWN;
            initializeFocusTraversalKeys();
            focusTraversalKeysEnabled = true;
        }

        Object keyOrNull;
        while(null != (keyOrNull = s.readObject())) {
            String key = ((String)keyOrNull).intern();

            if (componentListenerK == key)
                addComponentListener((ComponentListener)(s.readObject()));

            else if (focusListenerK == key)
                addFocusListener((FocusListener)(s.readObject()));

            else if (keyListenerK == key)
                addKeyListener((KeyListener)(s.readObject()));

            else if (mouseListenerK == key)
                addMouseListener((MouseListener)(s.readObject()));

            else if (mouseMotionListenerK == key)
                addMouseMotionListener((MouseMotionListener)(s.readObject()));

            else if (inputMethodListenerK == key)
                addInputMethodListener((InputMethodListener)(s.readObject()));

            else // skip value for unrecognized key
                s.readObject();

        }

        // Read the component's orientation if it's present
        Object orient = null;

        try {
            orient = s.readObject();
        } catch (java.io.OptionalDataException e) {
            // JDK 1.1 instances will not have this optional data.
            // e.eof will be true to indicate that there is no more
            // data available for this object.
            // If e.eof is not true, throw the exception as it
            // might have been caused by reasons unrelated to
            // componentOrientation.

            if (!e.eof)  {
                throw (e);
            }
        }

        if (orient != null) {
            componentOrientation = (ComponentOrientation)orient;
        } else {
            componentOrientation = ComponentOrientation.UNKNOWN;
        }

        try {
            while(null != (keyOrNull = s.readObject())) {
                String key = ((String)keyOrNull).intern();

                if (hierarchyListenerK == key) {
                    addHierarchyListener((HierarchyListener)(s.readObject()));
                }
                else if (hierarchyBoundsListenerK == key) {
                    addHierarchyBoundsListener((HierarchyBoundsListener)
                                               (s.readObject()));
                }
                else {
                    // skip value for unrecognized key
                    s.readObject();
                }
            }
        } catch (java.io.OptionalDataException e) {
            // JDK 1.1/1.2 instances will not have this optional data.
            // e.eof will be true to indicate that there is no more
            // data available for this object.
            // If e.eof is not true, throw the exception as it
            // might have been caused by reasons unrelated to
            // hierarchy and hierarchyBounds listeners.

            if (!e.eof)  {
                throw (e);
            }
        }

        try {
            while (null != (keyOrNull = s.readObject())) {
                String key = ((String)keyOrNull).intern();

                if (mouseWheelListenerK == key) {
                    addMouseWheelListener((MouseWheelListener)(s.readObject()));
                }
                else {
                    // skip value for unrecognized key
                    s.readObject();
                }
            }
        } catch (java.io.OptionalDataException e) {
            // pre-1.3 instances will not have this optional data.
            // e.eof will be true to indicate that there is no more
            // data available for this object.
            // If e.eof is not true, throw the exception as it
            // might have been caused by reasons unrelated to
            // mouse wheel listeners

            if (!e.eof)  {
                throw (e);
            }
        }

        if (popups != null) {
            int npopups = popups.size();
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = (PopupMenu)popups.elementAt(i);
                popup.parent = this;
            }
        }
    }

    