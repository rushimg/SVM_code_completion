// http://stackoverflow.com/questions/5958169/how-to-merge-two-sorted-arrays-into-a-sorted-array


public static long[] merge2SortedAndRemoveDublicates(long[] a, long[] b) {
    long[] answer = new long[a.length + b.length];
    int i = 0, j = 0, k = 0;
    long tmp;
    while (i < a.length && j < b.length) {
        tmp = a[i] < b[j] ? a[i++] : b[j++];
        for ( ; i < a.length && a[i] == tmp; i++);
        for ( ; j < b.length && b[j] == tmp; j++);
        answer[k++] = tmp;
    }
    while (i < a.length) {
        tmp = a[i++];
        for ( ; i < a.length && a[i] == tmp; i++);
        answer[k++] = tmp;
    }
    while (j < b.length) {
        tmp = b[j++];
        for ( ; j < b.length && b[j] == tmp; j++);
        answer[k++] = tmp;
    }
    return Arrays.copyOf(answer, k);
}

