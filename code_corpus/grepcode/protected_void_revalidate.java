
        protected void revalidate() {
            revalidate(true);
        }

        void revalidate(boolean checkSize) {
            validatedContents = false;

            if (backBuffers == null) {
                return;
            }

            if (checkSize) {
                Insets insets = getInsets_NoClientCode();
                if (getWidth() != width || getHeight() != height ||
                    !insets.equals(this.insets)) {
                    // component has been resized; recreate the backbuffers
                    createBackBuffers(backBuffers.length);
                    validatedContents = true;
                }
            }

            // now validate the backbuffer
            GraphicsConfiguration gc = getGraphicsConfiguration_NoClientCode();
            int returnCode =
                backBuffers[backBuffers.length - 1].validate(gc);
            if (returnCode == VolatileImage.IMAGE_INCOMPATIBLE) {
                if (checkSize) {
                    createBackBuffers(backBuffers.length);
                    // backbuffers were recreated, so validate again
                    backBuffers[backBuffers.length - 1].validate(gc);
                }
                // else case means we're called from Swing on the toolkit
                // thread, don't recreate buffers as that'll deadlock
                // (creating VolatileImages invokes getting GraphicsConfig
                // which grabs treelock).
                validatedContents = true;
            } else if (returnCode == VolatileImage.IMAGE_RESTORED) {
                validatedContents = true;
            }
        }

        