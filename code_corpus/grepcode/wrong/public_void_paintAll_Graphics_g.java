
    public void paintAll(Graphics g) {
        if (isShowing()) {
            GraphicsCallback.PeerPaintCallback.getInstance().
                runOneComponent(this, new Rectangle(0, 0, width, height),
                                g, g.getClip(),
                                GraphicsCallback.LIGHTWEIGHTS |
                                GraphicsCallback.HEAVYWEIGHTS);
        }
    }

    