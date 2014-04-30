
    public static int binarySearchFromTo(Object[] list, Object key, int from, int to, java.util.Comparator comparator) {
        Object midVal;
        while (from <= to) {
            int mid = (from + to) / 2;
            midVal = list[mid];
            int cmp = comparator.compare(midVal, key);

            if (cmp < 0)
                from = mid + 1;
            else if (cmp > 0)
                to = mid - 1;
            else
                return mid; // key found
        }
        return -(from + 1); // key not found.
    }

    