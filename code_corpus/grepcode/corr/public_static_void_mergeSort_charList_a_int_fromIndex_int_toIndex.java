
    public static void mergeSort(char[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        char aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex);
    }

    