
        protected FlipBufferStrategy(int numBuffers, BufferCapabilities caps)
            throws AWTException
        {
            if (!(Component.this instanceof Window) &&
                !(Component.this instanceof Canvas))
            {
                throw new ClassCastException(
                    "Component must be a Canvas or Window");
            }
            this.numBuffers = numBuffers;
            this.caps = caps;
            createBuffers(numBuffers, caps);
        }

        