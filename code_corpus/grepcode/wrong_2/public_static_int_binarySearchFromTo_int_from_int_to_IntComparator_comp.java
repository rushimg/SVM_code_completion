
    public static int binarySearchFromTo(int from, int to, IntComparator comp) {
        final int dummy = 0;
        while (from <= to) {
            int mid = (from + to) / 2;
            int comparison = comp.compare(dummy, mid);
            if (comparison < 0)
                from = mid + 1;
            else if (comparison > 0)
                to = mid - 1;
            else
                return mid; // key found
        }
        return -(from + 1); // key not found.
    }

    private static int lower_bound(int[] array, int first, int last, int x) {
        int len = last - first;
        while (len > 0) {
            int half = len / 2;
            int middle = first + half;
            if (array[middle] < x) {
                first = middle + 1;
                len -= half + 1;
            } else
                len = half;
        }
        return first;
    }

    private static int upper_bound(int[] array, int first, int last, int x) {
        int len = last - first;
        while (len > 0) {
            int half = len / 2;
            int middle = first + half;
            if (x < array[middle])
                len = half;
            else {
                first = middle + 1;
                len -= half + 1;
            }
        }
        return first;
    }

    private static void inplace_merge(int[] array, int first, int middle, int last) {
        if (first >= middle || middle >= last)
            return;
        if (last - first == 2) {
            if (array[middle] < array[first]) {
                int tmp = array[first];
                array[first] = array[middle];
                array[middle] = tmp;
            }
            return;
        }
        int firstCut;
        int secondCut;
        if (middle - first > last - middle) {
            firstCut = first + (middle - first) / 2;
            secondCut = lower_bound(array, middle, last, array[firstCut]);
        } else {
            secondCut = middle + (last - middle) / 2;
            firstCut = upper_bound(array, first, middle, array[secondCut]);
        }

        // rotate(array, firstCut, middle, secondCut);
        // is manually inlined for speed (jitter inlining seems to work only for
        // small call depths, even if methods are "static private")
        // speedup = 1.7
        // begin inline
        int first2 = firstCut;
        int middle2 = middle;
        int last2 = secondCut;
        if (middle2 != first2 && middle2 != last2) {
            int first1 = first2;
            int last1 = middle2;
            int tmp;
            while (first1 < --last1) {
                tmp = array[first1];
                array[last1] = array[first1];
                array[first1++] = tmp;
            }
            first1 = middle2;
            last1 = last2;
            while (first1 < --last1) {
                tmp = array[first1];
                array[last1] = array[first1];
                array[first1++] = tmp;
            }
            first1 = first2;
            last1 = last2;
            while (first1 < --last1) {
                tmp = array[first1];
                array[last1] = array[first1];
                array[first1++] = tmp;
            }
        }
        // end inline

        middle = firstCut + (secondCut - middle);
        inplace_merge(array, first, firstCut, middle);
        inplace_merge(array, middle, secondCut, last);
    }

    