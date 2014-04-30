double fibbonaci(int n){
    double prev=0d, next=1d, result=0d;
    for (int i = 1; i <n; i++) {
        result=prev+next;
        prev=next;
        next=result;
    }
    return result;
}
