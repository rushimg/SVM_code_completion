public DoubleMatrix2D sort(DoubleMatrix2D matrix, final double[] aggregates) {
        int rows = matrix.rows();
        if (aggregates.length != rows)
            throw new IndexOutOfBoundsException("aggregates.length != matrix.rows()");

        // set up index reordering
        final int[] indexes = new int[rows];
        for (int i = rows; --i >= 0;)
            indexes[i] = i;

        // compares two aggregates at a time
        cern.colt.function.tint.IntComparator comp = new cern.colt.function.tint.IntComparator() {
            public int compare(int x, int y) {
                double a = aggregates[x];
                double b = aggregates[y];
                if (a != a || b != b)
                    return compareNaN(a, b); // swap NaNs to the end
                return a < b ? -1 : (a == b) ? 0 : 1;
            }
        };
        // swaps aggregates and reorders indexes
        cern.colt.Swapper swapper = new cern.colt.Swapper() {
            public void swap(int x, int y) {
                int t1;
                double t2;
                t1 = indexes[x];
                indexes[x] = indexes[y];
                indexes[y] = t1;
                t2 = aggregates[x];
                aggregates[x] = aggregates[y];
                aggregates[y] = t2;
            }
        };

        // sort indexes and aggregates
        runSort(0, rows, comp, swapper);

        // view the matrix according to the reordered row indexes
        // take all columns in the original order
        return matrix.viewSelection(indexes, null);
    }
