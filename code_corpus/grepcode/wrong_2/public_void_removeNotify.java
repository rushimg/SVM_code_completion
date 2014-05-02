
    public void removeNotify() {
        KeyboardFocusManager.clearMostRecentFocusOwner(this);
        if (KeyboardFocusManager.getCurrentKeyboardFocusManager().
            getPermanentFocusOwner() == this)
        {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().
                setGlobalPermanentFocusOwner(null);
        }

        synchronized (getTreeLock()) {
            if (isFocusOwner() && KeyboardFocusManager.isAutoFocusTransferEnabledFor(this)) {
                transferFocus(true);
            }

            if (getContainer() != null && isAddNotifyComplete) {
                getContainer().decreaseComponentCount(this);
            }

            int npopups = (popups != null? popups.size() : 0);
            for (int i = 0 ; i < npopups ; i++) {
                PopupMenu popup = (PopupMenu)popups.elementAt(i);
                popup.removeNotify();
            }
            // If there is any input context for this component, notify
            // that this component is being removed. (This has to be done
            // before hiding peer.)
            if ((eventMask & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    inputContext.removeNotify(this);
                }
            }

            ComponentPeer p = peer;
            if (p != null) {
                boolean isLightweight = isLightweight();

                if (bufferStrategy instanceof FlipBufferStrategy) {
                    ((FlipBufferStrategy)bufferStrategy).destroyBuffers();
                }

                if (dropTarget != null) dropTarget.removeNotify(peer);

                // Hide peer first to stop system events such as cursor moves.
                if (visible) {
                    p.setVisible(false);
                }

                peer = null; // Stop peer updates.
                peerFont = null;

                Toolkit.getEventQueue().removeSourceEvents(this, false);
                KeyboardFocusManager.getCurrentKeyboardFocusManager().
                    discardKeyEvents(this);

                p.dispose();

                mixOnHiding(isLightweight);

                isAddNotifyComplete = false;
                // Nullifying compoundShape means that the component has normal shape
                // (or has no shape at all).
                this.compoundShape = null;
            }

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

    