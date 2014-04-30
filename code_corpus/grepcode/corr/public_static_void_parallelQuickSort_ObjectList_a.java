
    public static void parallelQuickSort(Object[] a) {
        if ((ConcurrencyUtils.getNumberOfThreads() > 1) && (a.length >= ConcurrencyUtils.getThreadsBeginN_1D())) {
            ParallelQuickSort.quickSort(a, 0, a.length, ConcurrencyUtils.prevPow2(2 * ConcurrencyUtils
                    .getNumberOfThreads()));
        } else {
            quickSort1(a, 0, a.length);
        }
    }

    