
    int getBoundsOp() {
        assert Thread.holdsLock(getTreeLock());
        return boundsOp;
    }

    void setBoundsOp(int op) {
        assert Thread.holdsLock(getTreeLock());
        if (op == ComponentPeer.RESET_OPERATION) {
            boundsOp = ComponentPeer.DEFAULT_OPERATION;
        } else
            if (boundsOp == ComponentPeer.DEFAULT_OPERATION) {
                boundsOp = op;
            }
    }

    // Whether this Component has had the background erase flag
    // specified via SunToolkit.disableBackgroundErase(). This is
    // needed in order to make this function work on X11 platforms,
    // where currently there is no chance to interpose on the creation
    // of the peer and therefore the call to XSetBackground.
    transient boolean backgroundEraseDisabled;

    static {
        AWTAccessor.setComponentAccessor(new AWTAccessor.ComponentAccessor() {
            public void setBackgroundEraseDisabled(Component comp, boolean disabled) {
                comp.backgroundEraseDisabled = disabled;
            }
            public boolean getBackgroundEraseDisabled(Component comp) {
                return comp.backgroundEraseDisabled;
            }
            public Rectangle getBounds(Component comp) {
                return new Rectangle(comp.x, comp.y, comp.width, comp.height);
            }
            public void setMixingCutoutShape(Component comp, Shape shape) {
                Region region = shape == null ?  null :
                    Region.getInstance(shape, null);

                synchronized (comp.getTreeLock()) {
                    boolean needShowing = false;
                    boolean needHiding = false;

                    if (!comp.isNonOpaqueForMixing()) {
                        needHiding = true;
                    }

                    comp.mixingCutoutRegion = region;

                    if (!comp.isNonOpaqueForMixing()) {
                        needShowing = true;
                    }

                    if (comp.isMixingNeeded()) {
                        if (needHiding) {
                            comp.mixOnHiding(comp.isLightweight());
                        }
                        if (needShowing) {
                            comp.mixOnShowing();
                        }
                    }
                }
            }

            public void setGraphicsConfiguration(Component comp,
                    GraphicsConfiguration gc)
            {
                comp.setGraphicsConfiguration(gc);
            }
            public boolean requestFocus(Component comp, CausedFocusEvent.Cause cause) {
                return comp.requestFocus(cause);
            }
            public boolean canBeFocusOwner(Component comp) {
                return comp.canBeFocusOwner();
            }

            public boolean isVisible(Component comp) {
                return comp.isVisible_NoClientCode();
            }
            public void setRequestFocusController
                (RequestFocusController requestController)
            {
                 Component.setRequestFocusController(requestController);
            }
            public AppContext getAppContext(Component comp) {
                 return comp.appContext;
            }
            public void setAppContext(Component comp, AppContext appContext) {
                 comp.appContext = appContext;
            }
            public Container getParent(Component comp) {
                return comp.getParent_NoClientCode();
            }
            public void setParent(Component comp, Container parent) {
                comp.parent = parent;
            }
            public void setSize(Component comp, int width, int height) {
                comp.width = width;
                comp.height = height;
            }
            public Point getLocation(Component comp) {
                return comp.location_NoClientCode();
            }
            public void setLocation(Component comp, int x, int y) {
                comp.x = x;
                comp.y = y;
            }
            public boolean isEnabled(Component comp) {
                return comp.isEnabledImpl();
            }
            public boolean isDisplayable(Component comp) {
                return comp.peer != null;
            }
            public Cursor getCursor(Component comp) {
                return comp.getCursor_NoClientCode();
            }
            public ComponentPeer getPeer(Component comp) {
                return comp.peer;
            }
            public void setPeer(Component comp, ComponentPeer peer) {
                comp.peer = peer;
            }
            public boolean isLightweight(Component comp) {
                return (comp.peer instanceof LightweightPeer);
            }
            public boolean getIgnoreRepaint(Component comp) {
                return comp.ignoreRepaint;
            }
            public int getWidth(Component comp) {
                return comp.width;
            }
            public int getHeight(Component comp) {
                return comp.height;
            }
            public int getX(Component comp) {
                return comp.x;
            }
            public int getY(Component comp) {
                return comp.y;
            }
            public Color getForeground(Component comp) {
                return comp.foreground;
            }
            public Color getBackground(Component comp) {
                return comp.background;
            }
            public void setBackground(Component comp, Color background) {
                comp.background = background;
            }
            public Font getFont(Component comp) {
                return comp.getFont_NoClientCode();
            }
            public void processEvent(Component comp, AWTEvent e) {
                comp.processEvent(e);
            }

            public AccessControlContext getAccessControlContext(Component comp) {
                return comp.getAccessControlContext();
            }
        });
    }

    