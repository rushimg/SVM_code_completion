public static void zdemo3() {
        DoubleSorting sort = quickSort;
        double[] values = { 0.5f, 1.5f, 2.5f, 3.5f };
        DoubleMatrix1D matrix = new DenseDoubleMatrix1D(values);
        cern.colt.function.tdouble.DoubleComparator comp = new cern.colt.function.tdouble.DoubleComparator() {
            public int compare(double a, double b) {
                double as = Math.sin(a);
                double bs = Math.sin(b);
                return as < bs ? -1 : as == bs ? 0 : 1;
            }
        };
        System.out.println("unsorted:" + matrix);

        DoubleMatrix1D sorted = sort.sort(matrix, comp);
        System.out.println("sorted  :" + sorted);

        // check whether it is really sorted
        sorted.assign(cern.jet.math.tdouble.DoubleFunctions.sin);
        /*
         * sorted.assign( new cern.colt.function.DoubleFunction() { public
         * double apply(double arg) { return Math.sin(arg); } } );
         */
        System.out.println("sined  :" + sorted);
    }
