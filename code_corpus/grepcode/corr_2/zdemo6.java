public static void zdemo6() {
        double[][] values = { { 3, 7, 0 }, { 2, 1, 0 }, { 2, 2, 0 }, { 1, 8, 0 }, { 2, 5, 0 }, { 7, 0, 0 },
                { 2, 3, 0 }, { 1, 0, 0 }, { 4, 0, 0 }, { 2, 0, 0 } };
        DoubleMatrix2D A = DoubleFactory2D.dense.make(values);
        DoubleMatrix2D B, C;
        System.out.println("\n\nunsorted:" + A);
        B = quickSort.sort(A, 1);
        C = quickSort.sort(B, 0);
        System.out.println("quick sorted  :" + C);

        B = mergeSort.sort(A, 1);
        C = mergeSort.sort(B, 0);
        System.out.println("merge sorted  :" + C);

    }
