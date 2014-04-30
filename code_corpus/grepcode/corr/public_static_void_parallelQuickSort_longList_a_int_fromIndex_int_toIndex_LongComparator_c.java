
    public static void parallelQuickSort(long[] a, int fromIndex, int toIndex, LongComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        if ((ConcurrencyUtils.getNumberOfThreads() > 1) && (a.length >= ConcurrencyUtils.getThreadsBeginN_1D())) {
            ParallelQuickSort.quickSort(a, fromIndex, toIndex - fromIndex, c, ConcurrencyUtils
                    .prevPow2(2 * ConcurrencyUtils.getNumberOfThreads()));
        } else {
            quickSort1(a, fromIndex, toIndex - fromIndex, c);
        }
    }

    