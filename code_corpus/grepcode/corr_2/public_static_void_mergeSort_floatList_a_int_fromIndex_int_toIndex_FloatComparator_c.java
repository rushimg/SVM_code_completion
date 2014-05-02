
    public static void mergeSort(float[] a, int fromIndex, int toIndex, FloatComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        float aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex, c);
    }

    