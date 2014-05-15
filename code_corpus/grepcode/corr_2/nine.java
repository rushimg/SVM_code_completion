public DoubleMatrix2D sort(DoubleMatrix2D matrix, int column) {
        if (column < 0 || column >= matrix.columns())
            throw new IndexOutOfBoundsException("column=" + column + ", matrix=" + AbstractFormatter.shape(matrix));

        int[] rowIndexes = new int[matrix.rows()]; // row indexes to reorder
        // instead of matrix itself
        for (int i = rowIndexes.length; --i >= 0;)
            rowIndexes[i] = i;

        final DoubleMatrix1D col = matrix.viewColumn(column);
        IntComparator comp = new IntComparator() {
            public int compare(int a, int b) {
                double av = col.getQuick(a);
                double bv = col.getQuick(b);
                if (av != av || bv != bv)
                    return compareNaN(av, bv); // swap NaNs to the end
                return av < bv ? -1 : (av == bv ? 0 : 1);
            }
        };

        runSort(rowIndexes, 0, rowIndexes.length, comp);

        // view the matrix according to the reordered row indexes
        // take all columns in the original order
        return matrix.viewSelection(rowIndexes, null);
    }
