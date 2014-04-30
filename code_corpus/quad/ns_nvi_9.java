public static long getFibonacci(int number){
if(number<=1) return number;
else return getFibonacci(number-1) + getFibonacci(number-2);
}
