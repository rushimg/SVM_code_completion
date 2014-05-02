
        protected void flip(BufferCapabilities.FlipContents flipAction) {
            if (peer != null) {
                Image backBuffer = getBackBuffer();
                if (backBuffer != null) {
                    peer.flip(0, 0,
                              backBuffer.getWidth(null),
                              backBuffer.getHeight(null), flipAction);
                }
            } else {
                throw new IllegalStateException(
                    "Component must have a valid peer");
            }
        }

        void flipSubRegion(int x1, int y1, int x2, int y2,
                      BufferCapabilities.FlipContents flipAction)
        {
            if (peer != null) {
                peer.flip(x1, y1, x2, y2, flipAction);
            } else {
                throw new IllegalStateException(
                    "Component must have a valid peer");
            }
        }

        