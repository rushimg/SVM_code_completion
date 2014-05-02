
        protected void destroyBuffers() {
            VSyncedBSManager.releaseVsync(this);
            if (peer != null) {
                peer.destroyBuffers();
            } else {
                throw new IllegalStateException(
                    "Component must have a valid peer");
            }
        }

        