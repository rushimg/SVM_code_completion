
    public NavigableMap<K,V> tailMap(K fromKey, boolean inclusive) {
        return new AscendingSubMap(this,
                                   false, fromKey, inclusive,
                                   true,  null,    true);
    }

    