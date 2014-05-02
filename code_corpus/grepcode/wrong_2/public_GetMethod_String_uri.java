
    public GetMethod(String uri) {
        super(uri);
        LOG.trace("enter GetMethod(String)");
        setFollowRedirects(true);
    }

    // --------------------------------------------------------- Public Methods

    