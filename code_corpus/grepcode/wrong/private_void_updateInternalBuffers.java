
        private void updateInternalBuffers() {
            // get the images associated with the draw buffer
            drawBuffer = getBackBuffer();
            if (drawBuffer instanceof VolatileImage) {
                drawVBuffer = (VolatileImage)drawBuffer;
            } else {
                drawVBuffer = null;
            }
        }

        