
    public void repaint(long tm, int x, int y, int width, int height) {
        if (this.peer instanceof LightweightPeer) {
            // Needs to be translated to parent coordinates since
            // a parent native container provides the actual repaint
            // services.  Additionally, the request is restricted to
            // the bounds of the component.
            if (parent != null) {
                if (x < 0) {
                    width += x;
                    x = 0;
                }
                if (y < 0) {
                    height += y;
                    y = 0;
                }

                int pwidth = (width > this.width) ? this.width : width;
                int pheight = (height > this.height) ? this.height : height;

                if (pwidth <= 0 || pheight <= 0) {
                    return;
                }

                int px = this.x + x;
                int py = this.y + y;
                parent.repaint(tm, px, py, pwidth, pheight);
            }
        } else {
            if (isVisible() && (this.peer != null) &&
                (width > 0) && (height > 0)) {
                PaintEvent e = new PaintEvent(this, PaintEvent.UPDATE,
                                              new Rectangle(x, y, width, height));
                Toolkit.getEventQueue().postEvent(e);
            }
        }
    }

    