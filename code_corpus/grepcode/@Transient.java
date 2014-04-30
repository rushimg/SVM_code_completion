
    @Transient
    public Font getFont() {
        return getFont_NoClientCode();
    }

    // NOTE: This method may be called by privileged threads.
    //       This functionality is implemented in a package-private method
    //       to insure that it cannot be overridden by client subclasses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    final Font getFont_NoClientCode() {
        Font font = this.font;
        if (font != null) {
            return font;
        }
        Container parent = this.parent;
        return (parent != null) ? parent.getFont_NoClientCode() : null;
    }

    