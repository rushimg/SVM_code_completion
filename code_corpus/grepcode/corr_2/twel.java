public DoubleMatrix3D sort(DoubleMatrix3D matrix, int row, int column) {
        if (row < 0 || row >= matrix.rows())
            throw new IndexOutOfBoundsException("row=" + row + ", matrix=" + AbstractFormatter.shape(matrix));
        if (column < 0 || column >= matrix.columns())
            throw new IndexOutOfBoundsException("column=" + column + ", matrix=" + AbstractFormatter.shape(matrix));

        int[] sliceIndexes = new int[matrix.slices()]; // indexes to reorder
        // instead of matrix
        // itself
        for (int i = sliceIndexes.length; --i >= 0;)
            sliceIndexes[i] = i;

        final DoubleMatrix1D sliceView = matrix.viewRow(row).viewColumn(column);
        IntComparator comp = new IntComparator() {
            public int compare(int a, int b) {
                double av = sliceView.getQuick(a);
                double bv = sliceView.getQuick(b);
                if (av != av || bv != bv)
                    return compareNaN(av, bv); // swap NaNs to the end
                return av < bv ? -1 : (av == bv ? 0 : 1);
            }
        };

        runSort(sliceIndexes, 0, sliceIndexes.length, comp);

        // view the matrix according to the reordered slice indexes
        // take all rows and columns in the original order
        return matrix.viewSelection(sliceIndexes, null, null);
    }
