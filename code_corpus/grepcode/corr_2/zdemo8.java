public static void zdemo8(int size) {
        System.out.println("\n\n");
        System.out.println("now initializing... ");

        final cern.jet.math.tdouble.DoubleFunctions F = cern.jet.math.tdouble.DoubleFunctions.functions;
        DoubleMatrix1D A = cern.colt.matrix.tdouble.DoubleFactory1D.dense.random(size);
        System.out.print("now quick sorting... ");
        cern.colt.Timer timer = new cern.colt.Timer().start();
        quickSort.sort(A);
        timer.stop().display();

        System.out.print("now merge sorting... ");
        timer.reset().start();
        mergeSort.sort(A);
        timer.stop().display();
    }
