
        protected Image getBackBuffer() {
            if (peer != null) {
                return peer.getBackBuffer();
            } else {
                throw new IllegalStateException(
                    "Component must have a valid peer");
            }
        }

        