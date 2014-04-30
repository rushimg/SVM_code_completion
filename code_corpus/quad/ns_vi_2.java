public void reverse3(int[] nums) {
    int[] values = new int[3];
    for (int i = 0; i <= nums.length - 1; i++) {
        for (int j = nums.length-1; j >= 0; j--) {
            values[i] = nums[j];
        }
    }
}
