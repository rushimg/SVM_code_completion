
    public static void quickSort(float[] a, int fromIndex, int toIndex, FloatComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    