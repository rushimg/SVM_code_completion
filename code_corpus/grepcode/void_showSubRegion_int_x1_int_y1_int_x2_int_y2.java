
        void showSubRegion(int x1, int y1, int x2, int y2) {
            if (backBuffers == null) {
                return;
            }
            // Adjust location to be relative to client area.
            x1 -= insets.left;
            x2 -= insets.left;
            y1 -= insets.top;
            y2 -= insets.top;
            Graphics g = getGraphics_NoClientCode();
            if (g == null) {
                // Not showing, bail
                return;
            }
            try {
                // First image copy is in terms of Frame's coordinates, need
                // to translate to client area.
                g.translate(insets.left, insets.top);
                for (int i = 0; i < backBuffers.length; i++) {
                    g.drawImage(backBuffers[i],
                                x1, y1, x2, y2,
                                x1, y1, x2, y2,
                                null);
                    g.dispose();
                    g = null;
                    g = backBuffers[i].getGraphics();
                }
            } finally {
                if (g != null) {
                    g.dispose();
                }
            }
        }

        