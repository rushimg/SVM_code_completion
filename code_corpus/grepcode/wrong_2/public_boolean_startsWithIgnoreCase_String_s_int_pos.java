
    public boolean startsWithIgnoreCase(String s, int pos) {
        byte[] b = buff;
        int len = s.length();
        if (b == null || len+pos > end-start) {
            return false;
        }
        int off = start+pos;
        for (int i = 0; i < len; i++) {
            if (Ascii.toLower( b[off++] ) != Ascii.toLower( s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public int indexOf( String src, int srcOff, int srcLen, int myOff ) {
        char first=src.charAt( srcOff );

        // Look for first char
        int srcEnd = srcOff + srcLen;

        mainLoop:
        for( int i=myOff+start; i <= (end - srcLen); i++ ) {
            if( buff[i] != first ) {
                continue;
            }
            // found first char, now look for a match
            int myPos=i+1;
            for( int srcPos=srcOff + 1; srcPos< srcEnd;) {
                if( buff[myPos++] != src.charAt( srcPos++ )) {
                    continue mainLoop;
                }
            }
            return i-start; // found it
        }
        return -1;
    }

    // -------------------- Hash code  --------------------

    @Override
    public int hashCode() {
        if (hasHashCode) {
            return hashCode;
        }
        int code = 0;

        code = hash();
        hashCode = code;
        hasHashCode = true;
        return code;
    }

    // normal hash.
    public int hash() {
        return hashBytes( buff, start, end-start);
    }

    private static int hashBytes( byte buff[], int start, int bytesLen ) {
        int max=start+bytesLen;
        byte bb[]=buff;
        int code=0;
        for (int i = start; i < max ; i++) {
            code = code * 37 + bb[i];
        }
        return code;
    }

    