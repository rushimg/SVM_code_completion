
    public void setBytes(byte[] b, int off, int len) {
        buff = b;
        start = off;
        end = start+ len;
        isSet=true;
        hasHashCode = false;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Charset getCharset() {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        return charset;
    }

    