
    private static int med3(Object x[], int a, int b, int c) {
        int ab = ((Comparable) x[a]).compareTo(x[b]);
        int ac = ((Comparable) x[a]).compareTo(x[c]);
        int bc = ((Comparable) x[b]).compareTo(x[c]);
        return (ab < 0 ? (bc < 0 ? b : ac < 0 ? c : a) : (bc > 0 ? b : ac > 0 ? c : a));
    }

    