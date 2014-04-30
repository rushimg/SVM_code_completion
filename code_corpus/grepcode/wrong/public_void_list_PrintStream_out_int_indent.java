
    public void list(PrintStream out, int indent) {
        for (int i = 0 ; i < indent ; i++) {
            out.print(" ");
        }
        out.println(this);
    }

    