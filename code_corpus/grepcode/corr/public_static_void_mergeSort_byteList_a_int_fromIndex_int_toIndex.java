
    public static void mergeSort(byte[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        byte aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex);
    }

    