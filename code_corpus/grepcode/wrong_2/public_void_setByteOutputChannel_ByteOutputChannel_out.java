
    public void setByteOutputChannel(ByteOutputChannel out) {
        this.out=out;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd( int i ) {
        end=i;
    }

    // -------------------- Adding data to the buffer --------------------
    public void append( byte b )
        throws IOException
    {
        makeSpace( 1 );

        // couldn't make space
        if( limit >0 && end >= limit ) {
            flushBuffer();
        }
        buff[end++]=b;
    }

    public void append( ByteChunk src )
        throws IOException
    {
        append( src.getBytes(), src.getStart(), src.getLength());
    }

    