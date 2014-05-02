
    public static void parallelQuickSort(int[] a, int fromIndex, int toIndex, IntComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        if ((ConcurrencyUtils.getNumberOfThreads() > 1) && (a.length >= ConcurrencyUtils.getThreadsBeginN_1D())) {
            ParallelQuickSort.quickSort(a, fromIndex, toIndex - fromIndex, c, ConcurrencyUtils
                    .prevPow2(2 * ConcurrencyUtils.getNumberOfThreads()));
        } else {
            quickSort1(a, fromIndex, toIndex - fromIndex, c);
        }
    }

    