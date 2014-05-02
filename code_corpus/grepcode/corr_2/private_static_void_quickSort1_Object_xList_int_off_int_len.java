
    private static void quickSort1(Object x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < SMALL) {
            for (int i = off; i < len + off; i++)
                for (int j = i; j > off && ((Comparable) x[j - 1]).compareTo(x[j]) > 0; j--)
                    swap(x, j, j - 1);
            return;
        }

        // Choose a partition element, v
        int m = off + len / 2; // Small arrays, middle element
        if (len > SMALL) {
            int l = off;
            int n = off + len - 1;
            if (len > MEDIUM) { // Big arrays, pseudomedian of 9
                int s = len / 8;
                l = med3(x, l, l + s, l + 2 * s);
                m = med3(x, m - s, m, m + s);
                n = med3(x, n - 2 * s, n - s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        Comparable v = (Comparable) x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while (true) {
            int comparison;
            while (b <= c && (comparison = ((Comparable) x[b]).compareTo(v)) <= 0) {
                if (comparison == 0)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && (comparison = ((Comparable) x[c]).compareTo(v)) >= 0) {
                if (comparison == 0)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a - off, b - a);
        vecswap(x, off, b - s, s);
        s = Math.min(d - c, n - d - 1);
        vecswap(x, b, n - s, s);

        // Recursively sort non-partition-elements
        if ((s = b - a) > 1)
            quickSort1(x, off, s);
        if ((s = d - c) > 1)
            quickSort1(x, n - s, s);
    }

    