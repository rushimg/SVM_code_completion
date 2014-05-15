public static void zdemo1() {
        DoubleSorting sort = quickSort;
        DoubleMatrix2D matrix = DoubleFactory2D.dense.descending(4, 3);
        DoubleMatrix1DComparator comp = new DoubleMatrix1DComparator() {
            public int compare(DoubleMatrix1D a, DoubleMatrix1D b) {
                double as = a.zSum();
                double bs = b.zSum();
                return as < bs ? -1 : as == bs ? 0 : 1;
            }
        };
        System.out.println("unsorted:" + matrix);
        System.out.println("sorted  :" + sort.sort(matrix, comp));
    }
