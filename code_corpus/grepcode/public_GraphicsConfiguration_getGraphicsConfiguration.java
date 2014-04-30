
    public GraphicsConfiguration getGraphicsConfiguration() {
        synchronized(getTreeLock()) {
            return getGraphicsConfiguration_NoClientCode();
        }
    }

    final GraphicsConfiguration getGraphicsConfiguration_NoClientCode() {
        return graphicsConfig;
    }

    void setGraphicsConfiguration(GraphicsConfiguration gc) {
        synchronized(getTreeLock()) {
            if (updateGraphicsData(gc)) {
                removeNotify();
                addNotify();
            }
        }
    }

    boolean updateGraphicsData(GraphicsConfiguration gc) {
        checkTreeLock();

        graphicsConfig = gc;

        ComponentPeer peer = getPeer();
        if (peer != null) {
            return peer.updateGraphicsData(gc);
        }
        return false;
    }

    