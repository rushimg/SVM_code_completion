
    private class ProxyCapabilities extends ExtendedBufferCapabilities {
        private BufferCapabilities orig;
        private ProxyCapabilities(BufferCapabilities orig) {
            super(orig.getFrontBufferCapabilities(),
                  orig.getBackBufferCapabilities(),
                  orig.getFlipContents() ==
                      BufferCapabilities.FlipContents.BACKGROUND ?
                      BufferCapabilities.FlipContents.BACKGROUND :
                      BufferCapabilities.FlipContents.COPIED);
            this.orig = orig;
        }
    }

    