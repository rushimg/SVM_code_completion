
    public int getStart() {
        return start;
    }

    public int getOffset() {
        return start;
    }

    public void setOffset(int off) {
        if (end < off ) {
            end=off;
        }
        start=off;
    }

    