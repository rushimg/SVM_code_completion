public static int[] bubbleSort(final int[] array) {
    if (array.length == 0)
        return array;

    int lastSwapIndex = 0;
    for (int i = 1; i < array.length; i++) {
        if (array[i - 1] > array[i]) {
            final int temp = array[i - 1];
            array[i - 1] = array[i];
            array[i] = temp;
            lastSwapIndex = i;
        }
    }
    System.arraycopy(bubbleSort(Arrays.copyOfRange(array, 0, lastSwapIndex)), 0, array, 0, lastSwapIndex);
    return array;
}
