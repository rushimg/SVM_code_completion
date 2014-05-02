
    private class FlipSubRegionBufferStrategy extends FlipBufferStrategy
        implements SubRegionShowable
    {

        protected FlipSubRegionBufferStrategy(int numBuffers,
                                              BufferCapabilities caps)
            throws AWTException
        {
            super(numBuffers, caps);
        }

        public void show(int x1, int y1, int x2, int y2) {
            showSubRegion(x1, y1, x2, y2);
        }

        // This is invoked by Swing on the toolkit thread.
        public boolean showIfNotLost(int x1, int y1, int x2, int y2) {
            if (!contentsLost()) {
                showSubRegion(x1, y1, x2, y2);
                return !contentsLost();
            }
            return false;
        }
    }

    