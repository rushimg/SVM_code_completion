
    public ColorModel getColorModel() {
        ComponentPeer peer = this.peer;
        if ((peer != null) && ! (peer instanceof LightweightPeer)) {
            return peer.getColorModel();
        } else if (GraphicsEnvironment.isHeadless()) {
            return ColorModel.getRGBdefault();
        } // else
        return getToolkit().getColorModel();
    }

    