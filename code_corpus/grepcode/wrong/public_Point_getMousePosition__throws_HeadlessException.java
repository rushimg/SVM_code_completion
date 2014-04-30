
    public Point getMousePosition() throws HeadlessException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException();
        }

        PointerInfo pi = (PointerInfo)java.security.AccessController.doPrivileged(
                                                                                  new java.security.PrivilegedAction() {
                                                                                      public Object run() {
                                                                                          return MouseInfo.getPointerInfo();
                                                                                      }
                                                                                  }
                                                                                  );

        synchronized (getTreeLock()) {
            Component inTheSameWindow = findUnderMouseInWindow(pi);
            if (!isSameOrAncestorOf(inTheSameWindow, true)) {
                return null;
            }
            return pointRelativeToComponent(pi.getLocation());
        }
    }

    