
    public static int binarySearchFromTo(double[] list, double key, int from, int to) {
        double midVal;
        while (from <= to) {
            int mid = (from + to) / 2;
            midVal = list[mid];
            if (midVal < key)
                from = mid + 1;
            else if (midVal > key)
                to = mid - 1;
            else
                return mid; // key found
        }
        return -(from + 1); // key not found.
    }

    