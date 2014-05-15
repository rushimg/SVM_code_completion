public DoubleMatrix3D sort(final DoubleMatrix3D matrix, final DoubleMatrix2DComparator c) {
        int[] sliceIndexes = new int[matrix.slices()]; // indexes to reorder
        // instead of matrix
        // itself
        for (int i = sliceIndexes.length; --i >= 0;)
            sliceIndexes[i] = i;

        final DoubleMatrix2D[] views = new DoubleMatrix2D[matrix.slices()]; // precompute
        // views
        // for
        // speed
        for (int i = views.length; --i >= 0;)
            views[i] = matrix.viewSlice(i);

        IntComparator comp = new IntComparator() {
            public int compare(int a, int b) {
                // return c.compare(matrix.viewSlice(a), matrix.viewSlice(b));
                return c.compare(views[a], views[b]);
            }
        };

        runSort(sliceIndexes, 0, sliceIndexes.length, comp);

        // view the matrix according to the reordered slice indexes
        // take all rows and columns in the original order
        return matrix.viewSelection(sliceIndexes, null, null);
    }
