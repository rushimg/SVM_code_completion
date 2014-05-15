public DoubleMatrix2D sort(final DoubleMatrix2D matrix, final DoubleMatrix1DComparator c) {
        int[] rowIndexes = new int[matrix.rows()]; // row indexes to reorder
        // instead of matrix itself
        for (int i = rowIndexes.length; --i >= 0;)
            rowIndexes[i] = i;

        final DoubleMatrix1D[] views = new DoubleMatrix1D[matrix.rows()]; // precompute
        // views
        // for
        // speed
        for (int i = views.length; --i >= 0;)
            views[i] = matrix.viewRow(i);

        IntComparator comp = new IntComparator() {
            public int compare(int a, int b) {
                // return c.compare(matrix.viewRow(a), matrix.viewRow(b));
                return c.compare(views[a], views[b]);
            }
        };

        runSort(rowIndexes, 0, rowIndexes.length, comp);

        // view the matrix according to the reordered row indexes
        // take all columns in the original order
        return matrix.viewSelection(rowIndexes, null);
    }
