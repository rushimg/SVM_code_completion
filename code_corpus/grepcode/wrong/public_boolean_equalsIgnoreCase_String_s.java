
    public boolean equalsIgnoreCase(String s) {
        byte[] b = buff;
        int blen = end-start;
        if (b == null || blen != s.length()) {
            return false;
        }
        int boff = start;
        for (int i = 0; i < blen; i++) {
            if (Ascii.toLower(b[boff++]) != Ascii.toLower(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals( ByteChunk bb ) {
        return equals( bb.getBytes(), bb.getStart(), bb.getLength());
    }

    public boolean equals( byte b2[], int off2, int len2) {
        byte b1[]=buff;
        if( b1==null && b2==null ) {
            return true;
        }

        int len=end-start;
        if ( len2 != len || b1==null || b2==null ) {
            return false;
        }

        int off1 = start;

        while ( len-- > 0) {
            if (b1[off1++] != b2[off2++]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals( CharChunk cc ) {
        return equals( cc.getChars(), cc.getStart(), cc.getLength());
    }

    public boolean equals( char c2[], int off2, int len2) {
        // XXX works only for enc compatible with ASCII/UTF !!!
        byte b1[]=buff;
        if( c2==null && b1==null ) {
            return true;
        }

        if (b1== null || c2==null || end-start != len2 ) {
            return false;
        }
        int off1 = start;
        int len=end-start;

        while ( len-- > 0) {
            if ( (char)b1[off1++] != c2[off2++]) {
                return false;
            }
        }
        return true;
    }

    