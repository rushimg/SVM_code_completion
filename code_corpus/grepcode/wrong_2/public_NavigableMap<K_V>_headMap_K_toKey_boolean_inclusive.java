
    public NavigableMap<K,V> headMap(K toKey, boolean inclusive) {
        return new AscendingSubMap(this,
                                   true,  null,  true,
                                   false, toKey, inclusive);
    }

    