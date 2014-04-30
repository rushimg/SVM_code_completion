
    public static void mergeSortInPlace(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        int length = toIndex - fromIndex;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = fromIndex; i < toIndex; i++) {
                for (int j = i; j > fromIndex && a[j - 1] > a[j]; j--) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
            return;
        }

        // Recursively sort halves
        int mid = (fromIndex + toIndex) / 2;
        mergeSortInPlace(a, fromIndex, mid);
        mergeSortInPlace(a, mid, toIndex);

        // If list is already sorted, nothing left to do. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (a[mid - 1] <= a[mid])
            return;

        // Merge sorted halves
        // jal.INT.Sorting.inplace_merge(a, fromIndex, mid, toIndex);
        inplace_merge(a, fromIndex, mid, toIndex);
    }

    