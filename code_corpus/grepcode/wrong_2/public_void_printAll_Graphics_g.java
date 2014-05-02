
    public void printAll(Graphics g) {
        if (isShowing()) {
            GraphicsCallback.PeerPrintCallback.getInstance().
                runOneComponent(this, new Rectangle(0, 0, width, height),
                                g, g.getClip(),
                                GraphicsCallback.LIGHTWEIGHTS |
                                GraphicsCallback.HEAVYWEIGHTS);
        }
    }

    