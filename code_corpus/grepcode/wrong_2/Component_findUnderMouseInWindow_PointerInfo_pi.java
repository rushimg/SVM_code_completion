
    Component findUnderMouseInWindow(PointerInfo pi) {
        if (!isShowing()) {
            return null;
        }
        Window win = getContainingWindow();
        if (!Toolkit.getDefaultToolkit().getMouseInfoPeer().isWindowUnderMouse(win)) {
            return null;
        }
        final boolean INCLUDE_DISABLED = true;
        Point relativeToWindow = win.pointRelativeToComponent(pi.getLocation());
        Component inTheSameWindow = win.findComponentAt(relativeToWindow.x,
                                                        relativeToWindow.y,
                                                        INCLUDE_DISABLED);
        return inTheSameWindow;
    }

    