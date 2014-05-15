 public static int [] mergesort(int start, int length, IndirectComparator comparator)
    {
        final int [] src = createOrderArray(start, length);

        if (length > 1)
        {
            final int [] dst = (int []) src.clone();
            topDownMergeSort(src, dst, 0, length, comparator);
            return dst;
        }

        return src;
    }
