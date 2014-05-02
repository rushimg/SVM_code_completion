
    public Image createImage(int width, int height) {
        ComponentPeer peer = this.peer;
        if (peer instanceof LightweightPeer) {
            if (parent != null) { return parent.createImage(width, height); }
            else { return null;}
        } else {
            return (peer != null) ? peer.createImage(width, height) : null;
        }
    }

    