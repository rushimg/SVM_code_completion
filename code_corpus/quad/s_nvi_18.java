public Integer[] sort(Integer[] orig) {
    Integer[] values = orig.clone();
    for (int i = 1; i < values.length; i++) {
        for (int j = i; j > 0 && values[j] < values[j - 1]; j--) {
            int temp = values[j - 1];
            values[j - 1] = values[j];
            values[j] = temp;
        }
    }
    return values;
}
