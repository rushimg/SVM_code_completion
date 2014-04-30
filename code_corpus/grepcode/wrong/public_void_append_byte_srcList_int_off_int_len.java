
    public void append( byte src[], int off, int len )
        throws IOException
    {
        // will grow, up to limit
        makeSpace( len );

        // if we don't have limit: makeSpace can grow as it wants
        if( limit < 0 ) {
            // assert: makeSpace made enough space
            System.arraycopy( src, off, buff, end, len );
            end+=len;
            return;
        }

        // Optimize on a common case.
        // If the buffer is empty and the source is going to fill up all the
        // space in buffer, may as well write it directly to the output,
        // and avoid an extra copy
        if ( len == limit && end == start && out != null ) {
            out.realWriteBytes( src, off, len );
            return;
        }
        // if we have limit and we're below
        if( len <= limit - end ) {
            // makeSpace will grow the buffer to the limit,
            // so we have space
            System.arraycopy( src, off, buff, end, len );
            end+=len;
            return;
        }

        // need more space than we can afford, need to flush
        // buffer

        // the buffer is already at ( or bigger than ) limit

        // We chunk the data into slices fitting in the buffer limit, although
        // if the data is written directly if it doesn't fit

        int avail=limit-end;
        System.arraycopy(src, off, buff, end, avail);
        end += avail;

        flushBuffer();

        int remain = len - avail;

        while (remain > (limit - end)) {
            out.realWriteBytes( src, (off + len) - remain, limit - end );
            remain = remain - (limit - end);
        }

        System.arraycopy(src, (off + len) - remain, buff, end, remain);
        end += remain;

    }


    // -------------------- Removing data from the buffer --------------------

    public int substract()
        throws IOException {

        if ((end - start) == 0) {
            if (in == null) {
                return -1;
            }
            int n = in.realReadBytes( buff, 0, buff.length );
            if (n < 0) {
                return -1;
            }
        }

        return (buff[start++] & 0xFF);

    }

    public byte substractB()
        throws IOException {

        if ((end - start) == 0) {
            if (in == null)
                return -1;
            int n = in.realReadBytes( buff, 0, buff.length );
            if (n < 0)
                return -1;
        }

        return (buff[start++]);

    }

    public int substract( byte src[], int off, int len )
        throws IOException {

        if ((end - start) == 0) {
            if (in == null) {
                return -1;
            }
            int n = in.realReadBytes( buff, 0, buff.length );
            if (n < 0) {
                return -1;
            }
        }

        int n = len;
        if (len > getLength()) {
            n = getLength();
        }
        System.arraycopy(buff, start, src, off, n);
        start += n;
        return n;

    }


    