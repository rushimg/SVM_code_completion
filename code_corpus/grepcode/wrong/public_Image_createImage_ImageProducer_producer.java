
    public Image createImage(ImageProducer producer) {
        ComponentPeer peer = this.peer;
        if ((peer != null) && ! (peer instanceof LightweightPeer)) {
            return peer.createImage(producer);
        }
        return getToolkit().createImage(producer);
    }

    