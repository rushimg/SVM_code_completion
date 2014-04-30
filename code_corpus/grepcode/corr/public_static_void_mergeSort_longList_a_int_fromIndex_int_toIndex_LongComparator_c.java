
    public static void mergeSort(long[] a, int fromIndex, int toIndex, LongComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        long aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex, c);
    }

    