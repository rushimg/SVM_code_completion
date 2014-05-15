public int[] sortIndex(final DoubleMatrix1D vector, IntComparator comp) {
        int[] indexes = new int[(int) vector.size()]; // row indexes to reorder
        // instead of matrix itself
        for (int i = indexes.length; --i >= 0;)
            indexes[i] = i;
        runSort(indexes, 0, indexes.length, comp);

        return indexes;
    }

