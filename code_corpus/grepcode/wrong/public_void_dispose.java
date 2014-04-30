
        public void dispose() {
            if (backBuffers != null) {
                for (int counter = backBuffers.length - 1; counter >= 0;
                     counter--) {
                    if (backBuffers[counter] != null) {
                        backBuffers[counter].flush();
                        backBuffers[counter] = null;
                    }
                }
            }
            if (Component.this.bufferStrategy == this) {
                Component.this.bufferStrategy = null;
            }
        }

        