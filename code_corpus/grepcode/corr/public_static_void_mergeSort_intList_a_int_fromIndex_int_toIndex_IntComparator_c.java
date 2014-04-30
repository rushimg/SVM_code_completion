
    public static void mergeSort(int[] a, int fromIndex, int toIndex, IntComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        int aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex, c);
    }

    