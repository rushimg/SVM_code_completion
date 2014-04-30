static String intToString(int num, int digits) {
    StringBuffer s = new StringBuffer(digits);
    int zeroes = digits - (int) (Math.log(num) / Math.log(10)) - 1; 
    for (int i = 0; i < zeroes; i++) {
        s.append(0);
    }
    return s.append(num).toString();
}
