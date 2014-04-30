
    public void addNotify() {
        synchronized (getTreeLock()) {
            ComponentPeer peer = this.peer;
            if (peer == null || peer instanceof LightweightPeer){
                if (peer == null) {
                    // Update both the Component's peer variable and the local
                    // variable we use for thread safety.
                    this.peer = peer = getToolkit().createComponent(this);
                }

                // This is a lightweight component which means it won't be
                // able to get window-related events by itself.  If any
                // have been enabled, then the nearest native container must
                // be enabled.
                if (parent != null) {
                    long mask = 0;
                    if ((mouseListener != null) || ((eventMask & AWTEvent.MOUSE_EVENT_MASK) != 0)) {
                        mask |= AWTEvent.MOUSE_EVENT_MASK;
                    }
                    if ((mouseMotionListener != null) ||
                        ((eventMask & AWTEvent.MOUSE_MOTION_EVENT_MASK) != 0)) {
                        mask |= AWTEvent.MOUSE_MOTION_EVENT_MASK;
                    }
                    if ((mouseWheelListener != null ) ||
                        ((eventMask & AWTEvent.MOUSE_WHEEL_EVENT_MASK) != 0)) {
                        mask |= AWTEvent.MOUSE_WHEEL_EVENT_MASK;
                    }
                    if (focusListener != null || (eventMask & AWTEvent.FOCUS_EVENT_MASK) != 0) {
                        mask |= AWTEvent.FOCUS_EVENT_MASK;
                    }
                    if (keyListener != null || (eventMask & AWTEvent.KEY_EVENT_MASK) != 0) {
                        mask |= AWTEvent.KEY_EVENT_MASK;
                    }
                    if (mask != 0) {
                        parent.proxyEnableEvents(mask);
                    }
                }
            } else {
                // It's native. If the parent is lightweight it will need some
                // help.
                Container parent = getContainer();
                if (parent != null && parent.isLightweight()) {
                    relocateComponent();
                    if (!parent.isRecursivelyVisibleUpToHeavyweightContainer())
                    {
                        peer.setVisible(false);
                    }
                }
            }
            invalidate();

            int npopups = (popups != null? popups.size() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = (PopupMenu)popups.elementAt(i);
                popup.addNotify();
            }

            if (dropTarget != null) dropTarget.addNotify(peer);

            peerFont = getFont();

            if (getContainer() != null && !isAddNotifyComplete) {
                getContainer().increaseComponentCount(this);
            }


            // Update stacking order
            updateZOrder();

            if (!isAddNotifyComplete) {
                mixOnShowing();
            }

            isAddNotifyComplete = true;

            if (hierarchyListener != null ||
                (eventMask & AWTEvent.HIERARCHY_EVENT_MASK) != 0 ||
                Toolkit.enabledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK)) {
                HierarchyEvent e =
                    new HierarchyEvent(this, HierarchyEvent.HIERARCHY_CHANGED,
                                       this, parent,
                                       HierarchyEvent.DISPLAYABILITY_CHANGED |
                                       ((isRecursivelyVisible())
                                        ? HierarchyEvent.SHOWING_CHANGED
                                        : 0));
                dispatchEvent(e);
            }
        }
    }

    