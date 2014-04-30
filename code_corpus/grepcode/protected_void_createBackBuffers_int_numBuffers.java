
        protected void createBackBuffers(int numBuffers) {
            if (numBuffers == 0) {
                backBuffers = null;
            } else {
                // save the current bounds
                width = getWidth();
                height = getHeight();
                insets = getInsets_NoClientCode();
                int iWidth = width - insets.left - insets.right;
                int iHeight = height - insets.top - insets.bottom;

                // It is possible for the component's width and/or height
                // to be 0 here.  Force the size of the backbuffers to
                // be > 0 so that creating the image won't fail.
                iWidth = Math.max(1, iWidth);
                iHeight = Math.max(1, iHeight);
                if (backBuffers == null) {
                    backBuffers = new VolatileImage[numBuffers];
                } else {
                    // flush any existing backbuffers
                    for (int i = 0; i < numBuffers; i++) {
                        if (backBuffers[i] != null) {
                            backBuffers[i].flush();
                            backBuffers[i] = null;
                        }
                    }
                }

                // create the backbuffers
                for (int i = 0; i < numBuffers; i++) {
                    backBuffers[i] = createVolatileImage(iWidth, iHeight);
                }
            }
        }

        