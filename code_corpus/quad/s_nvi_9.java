public static int[] insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int next = list[i];
            // find the insertion location while moving all larger element up
            int j = i;
            while (j > 0 && list[j - 1] > next) {
                list[j] = list[j - 1];
                j--;
            }
            // insert the element
            list[j] = next;
        }
        return list;
    }
