
    public Enumeration children() {
        if (children == null) {
            return EMPTY_ENUMERATION;
        } else {
            return children.elements();
        }
    }

    