
    public static void quickSort(char[] a, int fromIndex, int toIndex, CharComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    