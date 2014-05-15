public static void zdemo7(int rows, int columns, boolean print) {
        // for reliable benchmarks, call this method twice: once with small
        // dummy parameters to "warm up" the jitter, then with your real
        // work-load

        System.out.println("\n\n");
        System.out.println("now initializing... ");

        final cern.jet.math.tdouble.DoubleFunctions F = cern.jet.math.tdouble.DoubleFunctions.functions;
        DoubleMatrix2D A = cern.colt.matrix.tdouble.DoubleFactory2D.dense.make(rows, columns);
        A.assign(new cern.jet.random.tdouble.engine.DRand()); // initialize randomly

        double[] v1 = A.viewColumn(0).toArray();
        double[] v2 = A.viewColumn(0).toArray();
        System.out.print("now quick sorting... ");
        cern.colt.Timer timer = new cern.colt.Timer().start();
        quickSort.sort(A, 0);
        timer.stop().display();

        System.out.print("now merge sorting... ");
        timer.reset().start();
        mergeSort.sort(A, 0);
        timer.stop().display();

        System.out.print("now quick sorting with simple aggregation... ");
        timer.reset().start();
        quickSort.sort(A, v1);
        timer.stop().display();

        System.out.print("now merge sorting with simple aggregation... ");
        timer.reset().start();
        mergeSort.sort(A, v2);
        timer.stop().display();
    }

