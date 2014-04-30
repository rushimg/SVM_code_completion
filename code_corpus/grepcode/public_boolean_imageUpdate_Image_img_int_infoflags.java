
    public boolean imageUpdate(Image img, int infoflags,
                               int x, int y, int w, int h) {
        int rate = -1;
        if ((infoflags & (FRAMEBITS|ALLBITS)) != 0) {
            rate = 0;
        } else if ((infoflags & SOMEBITS) != 0) {
            if (isInc) {
                rate = incRate;
                if (rate < 0) {
                    rate = 0;
                }
            }
        }
        if (rate >= 0) {
            repaint(rate, 0, 0, width, height);
        }
        return (infoflags & (ALLBITS|ABORT)) == 0;
    }

    