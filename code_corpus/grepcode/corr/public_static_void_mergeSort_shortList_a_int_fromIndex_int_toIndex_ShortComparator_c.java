
    public static void mergeSort(short[] a, int fromIndex, int toIndex, ShortComparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        short aux[] = a.clone();
        mergeSort1(aux, a, fromIndex, toIndex, c);
    }

    private static void mergeSort1(byte src[], byte dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(byte src[], byte dest[], int low, int high, ByteComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(char src[], char dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(char src[], char dest[], int low, int high, CharComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(double src[], double dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(double src[], double dest[], int low, int high, DoubleComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(float src[], float dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(float src[], float dest[], int low, int high, FloatComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(int src[], int dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(int src[], int dest[], int low, int high, IntComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(long src[], long dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(long src[], long dest[], int low, int high, LongComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(short src[], short dest[], int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid);
        mergeSort1(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort1(short src[], short dest[], int low, int high, ShortComparator c) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < SMALL) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int mid = (low + high) / 2;
        mergeSort1(dest, src, low, mid, c);
        mergeSort1(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    private static void mergeSort2(double a[], int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        final long NEG_ZERO_BITS = Double.doubleToLongBits(-0.0d);
        /*
         * The sort is done in three phases to avoid the expense of using NaN
         * and -0.0 aware comparisons during the main sort.
         