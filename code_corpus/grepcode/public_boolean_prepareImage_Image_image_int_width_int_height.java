
    public boolean prepareImage(Image image, int width, int height,
                                ImageObserver observer) {
        ComponentPeer peer = this.peer;
        if (peer instanceof LightweightPeer) {
            return (parent != null)
                ? parent.prepareImage(image, width, height, observer)
                : getToolkit().prepareImage(image, width, height, observer);
        } else {
            return (peer != null)
                ? peer.prepareImage(image, width, height, observer)
                : getToolkit().prepareImage(image, width, height, observer);
        }
    }

    