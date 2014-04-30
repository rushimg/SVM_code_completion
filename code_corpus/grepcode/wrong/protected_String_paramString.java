
    protected String paramString() {
        String thisName = getName();
        String str = (thisName != null? thisName : "") + "," + x + "," + y + "," + width + "x" + height;
        if (!isValid()) {
            str += ",invalid";
        }
        if (!visible) {
            str += ",hidden";
        }
        if (!enabled) {
            str += ",disabled";
        }
        return str;
    }

    