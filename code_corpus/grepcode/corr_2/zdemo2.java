public static void zdemo2() {
        DoubleSorting sort = quickSort;
        DoubleMatrix3D matrix = DoubleFactory3D.dense.descending(4, 3, 2);
        DoubleMatrix2DComparator comp = new DoubleMatrix2DComparator() {
            public int compare(DoubleMatrix2D a, DoubleMatrix2D b) {
                double as = a.zSum();
                double bs = b.zSum();
                return as < bs ? -1 : as == bs ? 0 : 1;
            }
        };
        System.out.println("unsorted:" + matrix);
        System.out.println("sorted  :" + sort.sort(matrix, comp));
    }
