    public static void zdemo5(int rows, int columns, boolean print) {
        DoubleSorting sort = quickSort;
        // for reliable benchmarks, call this method twice: once with small
        // dummy parameters to "warm up" the jitter, then with your real
        // work-load

        System.out.println("\n\n");
        System.out.print("now initializing... ");
        cern.colt.Timer timer = new cern.colt.Timer().start();

        final cern.jet.math.tdouble.DoubleFunctions F = cern.jet.math.tdouble.DoubleFunctions.functions;
        DoubleMatrix2D A = cern.colt.matrix.tdouble.DoubleFactory2D.dense.make(rows, columns);
        A.assign(new cern.jet.random.tdouble.engine.DRand()); // initialize randomly
        timer.stop().display();

        // also benchmark copying in its several implementation flavours
        DoubleMatrix2D B = A.like();
        timer.reset().start();
        System.out.print("now copying... ");
        B.assign(A);
        timer.stop().display();

        timer.reset().start();
        System.out.print("now copying subrange... ");
        B.viewPart(0, 0, rows, columns).assign(A.viewPart(0, 0, rows, columns));
        timer.stop().display();
        // System.out.println(A);

        timer.reset().start();
        System.out.print("now copying selected... ");
        B.viewSelection(null, null).assign(A.viewSelection(null, null));
        timer.stop().display();

        System.out.print("now sorting - quick version with precomputation... ");
        timer.reset().start();
        // THE QUICK VERSION (takes some 10 secs)
        A = sort.sort(A, hep.aida.tdouble.bin.DoubleBinFunctions1D.median);
        // A = sort.sort(A,hep.aida.bin.BinFunctions1D.sumLog);
        timer.stop().display();

        // check results for correctness
        // WARNING: be sure NOT TO PRINT huge matrices unless you have tons of
        // main memory and time!!
        // so we just show the first 5 rows
        if (print) {
            int r = Math.min(rows, 5);
            hep.aida.tdouble.bin.DoubleBinFunction1D[] funs = { hep.aida.tdouble.bin.DoubleBinFunctions1D.median,
                    hep.aida.tdouble.bin.DoubleBinFunctions1D.sumLog,
                    hep.aida.tdouble.bin.DoubleBinFunctions1D.geometricMean };
            String[] rowNames = new String[r];
            String[] columnNames = new String[columns];
            for (int i = columns; --i >= 0;)
                columnNames[i] = Integer.toString(i);
            for (int i = r; --i >= 0;)
                rowNames[i] = Integer.toString(i);
            System.out.println("first part of sorted result = \n"
                    + new cern.colt.matrix.tdouble.algo.DoubleFormatter("%G").toTitleString(A
                            .viewPart(0, 0, r, columns), rowNames, columnNames, null, null, null, funs));
        }

        System.out.print("now sorting - slow version... ");
        A = B;
        cern.colt.matrix.tdouble.algo.DoubleMatrix1DComparator fun = new cern.colt.matrix.tdouble.algo.DoubleMatrix1DComparator() {
            public int compare(DoubleMatrix1D x, DoubleMatrix1D y) {
                double a = cern.colt.matrix.tdouble.algo.DoubleStatistic.bin(x).median();
                double b = cern.colt.matrix.tdouble.algo.DoubleStatistic.bin(y).median();
                // double a = x.aggregate(F.plus,F.log);
                // double b = y.aggregate(F.plus,F.log);
                return a < b ? -1 : (a == b) ? 0 : 1;
            }
        };
        timer.reset().start();
        A = sort.sort(A, fun);
        timer.stop().display();
    }
