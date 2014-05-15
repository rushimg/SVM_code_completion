protected static void zdemo4() {
        double[] values1 = { 0, 1, 2, 3 };
        double[] values2 = { 0, 2, 4, 6 };
        DoubleMatrix1D matrix1 = new DenseDoubleMatrix1D(values1);
        DoubleMatrix1D matrix2 = new DenseDoubleMatrix1D(values2);
        System.out.println("m1:" + matrix1);
        System.out.println("m2:" + matrix2);

        matrix1.assign(matrix2, cern.jet.math.tdouble.DoubleFunctions.pow);

        /*
         * matrix1.assign(matrix2, new cern.colt.function.DoubleDoubleFunction() {
         * public double apply(double x, double y) { return Math.pow(x,y); } } );
         */

        System.out.println("applied:" + matrix1);
    }
