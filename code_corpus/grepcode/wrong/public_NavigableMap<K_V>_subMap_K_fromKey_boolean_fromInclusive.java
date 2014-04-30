
    public NavigableMap<K,V> subMap(K fromKey, boolean fromInclusive,
                                    K toKey,   boolean toInclusive) {
        return new AscendingSubMap(this,
                                   false, fromKey, fromInclusive,
                                   false, toKey,   toInclusive);
    }

    