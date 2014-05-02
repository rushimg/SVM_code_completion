
    public static void quickSort(byte[] a, int fromIndex, int toIndex, ByteComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        quickSort1(a, fromIndex, toIndex - fromIndex, c);
    }

    