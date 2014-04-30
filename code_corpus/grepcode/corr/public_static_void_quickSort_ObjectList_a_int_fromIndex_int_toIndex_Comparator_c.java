
    public static void quickSort(Object[] a, int fromIndex, int toIndex, Comparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    