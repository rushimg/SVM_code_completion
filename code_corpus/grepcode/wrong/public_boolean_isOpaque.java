
    public boolean isOpaque() {
        if (getPeer() == null) {
            return false;
        }
        else {
            return !isLightweight();
        }
    }


    