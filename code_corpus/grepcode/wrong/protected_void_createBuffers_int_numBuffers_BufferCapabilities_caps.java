
        protected void createBuffers(int numBuffers, BufferCapabilities caps)
            throws AWTException
        {
            if (numBuffers < 2) {
                throw new IllegalArgumentException(
                    "Number of buffers cannot be less than two");
            } else if (peer == null) {
                throw new IllegalStateException(
                    "Component must have a valid peer");
            } else if (caps == null || !caps.isPageFlipping()) {
                throw new IllegalArgumentException(
                    "Page flipping capabilities must be specified");
            }

            // save the current bounds
            width = getWidth();
            height = getHeight();

            if (drawBuffer != null) {
                // dispose the existing backbuffers
                drawBuffer = null;
                drawVBuffer = null;
                destroyBuffers();
                // ... then recreate the backbuffers
            }

            if (caps instanceof ExtendedBufferCapabilities) {
                ExtendedBufferCapabilities ebc =
                    (ExtendedBufferCapabilities)caps;
                if (ebc.getVSync() == VSYNC_ON) {
                    // if this buffer strategy is not allowed to be v-synced,
                    // change the caps that we pass to the peer but keep on
                    // trying to create v-synced buffers;
                    // do not throw IAE here in case it is disallowed, see
                    // ExtendedBufferCapabilities for more info
                    if (!VSyncedBSManager.vsyncAllowed(this)) {
                        caps = ebc.derive(VSYNC_DEFAULT);
                    }
                }
            }

            peer.createBuffers(numBuffers, caps);
            updateInternalBuffers();
        }

        