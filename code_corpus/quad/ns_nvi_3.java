public static int sum(int[] array, int index) {
    if (index == 0) {
        return array[0];
    } else {
        return array[index] + sum(array, index - 1);
    }
}
