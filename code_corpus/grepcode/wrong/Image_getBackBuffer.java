
        Image getBackBuffer() {
            if (backBuffers != null) {
                return backBuffers[backBuffers.length - 1];
            } else {
                return null;
            }
        }

        