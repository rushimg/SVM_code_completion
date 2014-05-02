
    public static void mergeSort(short[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        short aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex);
    }

    