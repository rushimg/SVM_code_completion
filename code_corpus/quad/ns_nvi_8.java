public long getFibonacci( int number) {
    if ( number <=2) {
        return 1;
    }
    long lRet = 0;
    lRet = getFibonacci( number -1) + getFibonacci( number -2);
    return lRet;
}
