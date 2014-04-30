
    public static void quickSort(double[] a, int fromIndex, int toIndex, DoubleComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    