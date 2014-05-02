
    public ByteChunk() {
        // NO-OP
    }

    public ByteChunk( int initial ) {
        allocate( initial, -1 );
    }

    public boolean isNull() {
        return ! isSet; // buff==null;
    }

    