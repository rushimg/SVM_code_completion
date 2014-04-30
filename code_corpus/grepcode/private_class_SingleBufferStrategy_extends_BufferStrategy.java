
    private class SingleBufferStrategy extends BufferStrategy {

        private BufferCapabilities caps;

        public SingleBufferStrategy(BufferCapabilities caps) {
            this.caps = caps;
        }
        public BufferCapabilities getCapabilities() {
            return caps;
        }
        public Graphics getDrawGraphics() {
            return getGraphics();
        }
        public boolean contentsLost() {
            return false;
        }
        public boolean contentsRestored() {
            return false;
        }
        public void show() {
            // Do nothing
        }
    } // Inner class SingleBufferStrategy

    