public static void selectionSort1(int[] x) {
    for (int i=0; i<x.length-1; i++) {
        for (int j=i+1; j<x.length; j++) {
            if (x[i] > x[j]) {
                //... Exchange elements
                int temp = x[i];
                x[i] = x[j];
                x[j] = temp;
            }
        }
    }
}
