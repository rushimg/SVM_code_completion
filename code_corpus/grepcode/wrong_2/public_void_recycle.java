
    public void recycle() {
        //        buff = null;
        charset=null;
        start=0;
        end=0;
        isSet=false;
        hasHashCode = false;
    }

    public void reset() {
        buff=null;
    }

    // -------------------- Setup --------------------

    public void allocate( int initial, int limit  ) {
        if( buff==null || buff.length < initial ) {
            buff=new byte[initial];
        }
        this.limit=limit;
        start=0;
        end=0;
        isSet=true;
        hasHashCode = false;
    }

    