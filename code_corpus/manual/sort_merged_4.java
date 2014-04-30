//http://stackoverflow.com/questions/5958169/how-to-merge-two-sorted-arrays-into-a-sorted-array

private static int[] sortedArrayMerge(int a[], int b[]) {
    int result[] = new int[a.length +b.length];
    int i =0; int j = 0;int k = 0;
    while(i<a.length && j <b.length) {
        if(a[i]<b[j]) {
            result[k++] = a[i];
            i++;
        } else {
            result[k++] = b[j];
            j++;
        }
    }
    System.arraycopy(a, i, result, k, (a.length -i));
    System.arraycopy(b, j, result, k, (b.length -j));
    return result;
}
