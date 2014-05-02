
    public static void quickSort(short[] a, int fromIndex, int toIndex, ShortComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    