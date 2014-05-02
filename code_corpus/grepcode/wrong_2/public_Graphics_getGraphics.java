
    public Graphics getGraphics() {
        if (peer instanceof LightweightPeer) {
            // This is for a lightweight component, need to
            // translate coordinate spaces and clip relative
            // to the parent.
            if (parent == null) return null;
            Graphics g = parent.getGraphics();
            if (g == null) return null;
            if (g instanceof ConstrainableGraphics) {
                ((ConstrainableGraphics) g).constrain(x, y, width, height);
            } else {
                g.translate(x,y);
                g.setClip(0, 0, width, height);
            }
            g.setFont(getFont());
            return g;
        } else {
            ComponentPeer peer = this.peer;
            return (peer != null) ? peer.getGraphics() : null;
        }
    }

    final Graphics getGraphics_NoClientCode() {
        ComponentPeer peer = this.peer;
        if (peer instanceof LightweightPeer) {
            // This is for a lightweight component, need to
            // translate coordinate spaces and clip relative
            // to the parent.
            Container parent = this.parent;
            if (parent == null) return null;
            Graphics g = parent.getGraphics_NoClientCode();
            if (g == null) return null;
            if (g instanceof ConstrainableGraphics) {
                ((ConstrainableGraphics) g).constrain(x, y, width, height);
            } else {
                g.translate(x,y);
                g.setClip(0, 0, width, height);
            }
            g.setFont(getFont_NoClientCode());
            return g;
        } else {
            return (peer != null) ? peer.getGraphics() : null;
        }
    }

    