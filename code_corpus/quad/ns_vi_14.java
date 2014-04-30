public static void equalSum(int[] a) {
    int right = 0; 
    int left= sum(a);
         
    for (int j = a.length - 1; j >= 0; j--) {
        if (left == right)
            return j;
        right+= a[j];
        left-= a[j];
 
    }
}
