
    public static void mergeSort(double[] a, int fromIndex, int toIndex, DoubleComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        double aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex, c);
    }

    