public int[] sortIndex(final DoubleMatrix1D vector, final cern.colt.function.tdouble.DoubleComparator c) {
        int[] indexes = new int[(int) vector.size()]; // row indexes to reorder
        // instead of matrix itself
        for (int i = indexes.length; --i >= 0;)
            indexes[i] = i;
        IntComparator comp = null;
        if (vector instanceof DenseDoubleMatrix1D) {
            final double[] velems = (double[]) vector.elements();
            final int zero = (int) vector.index(0);
            final int stride = vector.stride();
            comp = new IntComparator() {
                public int compare(int a, int b) {
                    int idxa = zero + a * stride;
                    int idxb = zero + b * stride;
                    return c.compare(velems[idxa], velems[idxb]);
                }
            };
        } else {
            comp = new IntComparator() {
                public int compare(int a, int b) {
                    return c.compare(vector.getQuick(a), vector.getQuick(b));
                }
            };
        }

        runSort(indexes, 0, indexes.length, comp);

        return indexes;
    }
