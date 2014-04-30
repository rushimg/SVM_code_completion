
    public Dimension getSize(Dimension rv) {
        if (rv == null) {
            return new Dimension(getWidth(), getHeight());
        }
        else {
            rv.setSize(getWidth(), getHeight());
            return rv;
        }
    }

    