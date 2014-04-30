
    public static void quickSort(long[] a, int fromIndex, int toIndex, LongComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    