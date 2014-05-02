
    private void makeSpace(int count)
    {
        byte[] tmp = null;

        int newSize;
        int desiredSize=end + count;

        // Can't grow above the limit
        if( limit > 0 &&
            desiredSize > limit) {
            desiredSize=limit;
        }

        if( buff==null ) {
            if( desiredSize < 256 )
             {
                desiredSize=256; // take a minimum
            }
            buff=new byte[desiredSize];
        }

        // limit < buf.length ( the buffer is already big )
        // or we already have space XXX
        if( desiredSize <= buff.length ) {
            return;
        }
        // grow in larger chunks
        if( desiredSize < 2 * buff.length ) {
            newSize= buff.length * 2;
            if( limit >0 &&
                newSize > limit ) {
                newSize=limit;
            }
            tmp=new byte[newSize];
        } else {
            newSize= buff.length * 2 + count ;
            if( limit > 0 &&
                newSize > limit ) {
                newSize=limit;
            }
            tmp=new byte[newSize];
        }

        System.arraycopy(buff, start, tmp, 0, end-start);
        buff = tmp;
        tmp = null;
        end=end-start;
        start=0;
    }

    // -------------------- Conversion and getters --------------------

    @Override
    public String toString() {
        if (null == buff) {
            return null;
        } else if (end-start == 0) {
            return "";
        }
        return StringCache.toString(this);
    }

    public String toStringInternal() {
        if (charset == null) {
            charset = DEFAULT_CHARSET;
        }
        // new String(byte[], int, int, Charset) takes a defensive copy of the
        // entire byte array. This is expensive if only a small subset of the
        // bytes will be used. The code below is from Apache Harmony.
        CharBuffer cb;
        cb = charset.decode(ByteBuffer.wrap(buff, start, end-start));
        return new String(cb.array(), cb.arrayOffset(), cb.length());
    }

    public long getLong() {
        return Ascii.parseLong(buff, start,end-start);
    }


    // -------------------- equals --------------------

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ByteChunk) {
            return equals((ByteChunk) obj);
        }
        return false;
    }

    