
    public Rectangle getBounds(Rectangle rv) {
        if (rv == null) {
            return new Rectangle(getX(), getY(), getWidth(), getHeight());
        }
        else {
            rv.setBounds(getX(), getY(), getWidth(), getHeight());
            return rv;
        }
    }

    