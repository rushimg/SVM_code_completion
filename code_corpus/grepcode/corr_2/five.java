public int[] sortIndex(final DoubleMatrix1D vector) {
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
                    double av = velems[idxa];
                    double bv = velems[idxb];
                    if (av != av || bv != bv)
                        return compareNaN(av, bv); // swap NaNs to the end
                    return av < bv ? -1 : (av == bv ? 0 : 1);
                }
            };
        } else {
            comp = new IntComparator() {
                public int compare(int a, int b) {
                    double av = vector.getQuick(a);
                    double bv = vector.getQuick(b);
                    if (av != av || bv != bv)
                        return compareNaN(av, bv); // swap NaNs to the end
                    return av < bv ? -1 : (av == bv ? 0 : 1);
                }
            };
        }

        runSort(indexes, 0, indexes.length, comp);

        return indexes;
    }
