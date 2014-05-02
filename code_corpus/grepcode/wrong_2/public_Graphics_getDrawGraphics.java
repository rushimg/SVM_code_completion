
        public Graphics getDrawGraphics() {
            revalidate();
            Image backBuffer = getBackBuffer();
            if (backBuffer == null) {
                return getGraphics();
            }
            SunGraphics2D g = (SunGraphics2D)backBuffer.getGraphics();
            g.constrain(-insets.left, -insets.top,
                        backBuffer.getWidth(null) + insets.left,
                        backBuffer.getHeight(null) + insets.top);
            return g;
        }

        