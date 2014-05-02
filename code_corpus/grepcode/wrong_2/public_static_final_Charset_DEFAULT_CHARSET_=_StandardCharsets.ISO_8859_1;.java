
    public static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    private int hashCode=0;
    // did we compute the hashcode ?
    private boolean hasHashCode = false;

    // byte[]
    private byte[] buff;

    private int start=0;
    private int end;

    private Charset charset;

    private boolean isSet=false; // XXX

    // How much can it grow, when data is added
    private int limit=-1;

    private ByteInputChannel in = null;
    private ByteOutputChannel out = null;

    