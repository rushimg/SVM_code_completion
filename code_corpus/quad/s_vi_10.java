private static void BubbleSort(int[] num) {
 for (int i = 0; i < num.length; i++) {
    for (int x = 1; x < num.length - i; x++) {
        if (num[x - 1] > num[x]) {
            int temp = num[x - 1];
            num[x - 1] = num[x];
            num[x] = temp;

        }
    }
  }
}
