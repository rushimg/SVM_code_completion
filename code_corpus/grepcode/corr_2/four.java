private static void insertionSort(final int off, final int len, int [] order,
        IndirectComparator intComparator)
    {
        for (int i = off + 1; i < off + len; i++)
        {
            final int v = order[i];
            int j = i, t;
            while (j > off && intComparator.compare(t = order[j - 1], v) > 0)
            {
                order[j--] = t;
            }
            order[j] = v;
        }
    }
