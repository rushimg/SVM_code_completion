  private static void topDownMergeSort(int [] src, int [] dst, int fromIndex, int toIndex,
        IndirectComparator comp)
    {
        if (toIndex - fromIndex <= MIN_LENGTH_FOR_INSERTION_SORT)
        {
            insertionSort(fromIndex, toIndex - fromIndex, dst, comp);
            return;
        }

        final int mid = (fromIndex + toIndex) >>> 1;
        topDownMergeSort(dst, src, fromIndex, mid, comp);
        topDownMergeSort(dst, src, mid, toIndex, comp);

        /*
         * Both splits in of src are now sorted.
         */
        if (comp.compare(src[mid - 1], src[mid]) <= 0)
        {
            /*
             * If the lowest element in upper slice is larger than the highest element in
             * the lower slice, simply copy over, the data is fully sorted.
             */
            System.arraycopy(src, fromIndex, dst, fromIndex, toIndex - fromIndex);
        }
        else
        {
            /*
             * Run a manual merge.
             */
            for (int i = fromIndex, j = mid, k = fromIndex; k < toIndex; k++)
            {
                if (j == toIndex || (i < mid && comp.compare(src[i], src[j]) <= 0))
                {
                    dst[k] = src[i++];
                }
                else
                {
                    dst[k] = src[j++];
                }
            }
        }
    }
