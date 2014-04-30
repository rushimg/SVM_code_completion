
    public int checkImage(Image image, int width, int height,
                          ImageObserver observer) {
        ComponentPeer peer = this.peer;
        if (peer instanceof LightweightPeer) {
            return (parent != null)
                ? parent.checkImage(image, width, height, observer)
                : getToolkit().checkImage(image, width, height, observer);
        } else {
            return (peer != null)
                ? peer.checkImage(image, width, height, observer)
                : getToolkit().checkImage(image, width, height, observer);
        }
    }

    