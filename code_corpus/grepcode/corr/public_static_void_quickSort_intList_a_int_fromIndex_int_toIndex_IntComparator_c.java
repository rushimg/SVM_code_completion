
    public static void quickSort(int[] a, int fromIndex, int toIndex, IntComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    