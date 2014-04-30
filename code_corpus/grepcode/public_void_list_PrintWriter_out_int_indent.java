
    public void list(PrintWriter out, int indent) {
        for (int i = 0 ; i < indent ; i++) {
            out.print(" ");
        }
        out.println(this);
    }

    /*
     * Fetches the native container somewhere higher up in the component
     * tree that contains this component.
     