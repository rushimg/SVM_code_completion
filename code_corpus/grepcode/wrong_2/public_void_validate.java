
    public void validate() {
        synchronized (getTreeLock()) {
            ComponentPeer peer = this.peer;
            boolean wasValid = isValid();
            if (!wasValid && peer != null) {
                Font newfont = getFont();
                Font oldfont = peerFont;
                if (newfont != oldfont && (oldfont == null
                                           || !oldfont.equals(newfont))) {
                    peer.setFont(newfont);
                    peerFont = newfont;
                }
                peer.layout();
            }
            valid = true;
            if (!wasValid) {
                mixOnValidating();
            }
        }
    }

    