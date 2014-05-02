
        protected BltBufferStrategy(int numBuffers, BufferCapabilities caps) {
            this.caps = caps;
            createBackBuffers(numBuffers - 1);
        }

        