public static <T> int [] mergesort(T [] input, int start, int length,
        Comparator<? super T> comparator)
    {
        return mergesort(start, length, new IndirectComparator.DelegatingComparator<T>(
            input, comparator));
    }

