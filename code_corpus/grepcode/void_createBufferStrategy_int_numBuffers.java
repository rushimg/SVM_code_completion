
    void createBufferStrategy(int numBuffers,
                              BufferCapabilities caps) throws AWTException {
        // Check arguments
        if (numBuffers < 1) {
            throw new IllegalArgumentException(
                "Number of buffers must be at least 1");
        }
        if (caps == null) {
            throw new IllegalArgumentException("No capabilities specified");
        }
        // Destroy old buffers
        if (bufferStrategy != null) {
            bufferStrategy.dispose();
        }
        if (numBuffers == 1) {
            bufferStrategy = new SingleBufferStrategy(caps);
        } else {
            SunGraphicsEnvironment sge = (SunGraphicsEnvironment)
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            if (!caps.isPageFlipping() && sge.isFlipStrategyPreferred(peer)) {
                caps = new ProxyCapabilities(caps);
            }
            // assert numBuffers > 1;
            if (caps.isPageFlipping()) {
                bufferStrategy = new FlipSubRegionBufferStrategy(numBuffers, caps);
            } else {
                bufferStrategy = new BltSubRegionBufferStrategy(numBuffers, caps);
            }
        }
    }

    