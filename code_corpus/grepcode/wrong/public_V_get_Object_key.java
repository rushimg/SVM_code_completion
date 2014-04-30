
    public V get(Object key) {
        Entry<K,V> p = getEntry(key);
        return (p==null ? null : p.value);
    }

    public Comparator<? super K> comparator() {
        return comparator;
    }

    