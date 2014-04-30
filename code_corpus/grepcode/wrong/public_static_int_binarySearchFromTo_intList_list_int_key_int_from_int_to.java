
    public static int binarySearchFromTo(int[] list, int key, int from, int to) {
        int midVal;
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

        /*
         * // even for very short lists (0,1,2,3 elems) this is only 10% faster
         * while (from<=to && list[from++] < key) ; if (from<=to) { if
         * (list[--from] == key) return from; } return -(from + 1);
         