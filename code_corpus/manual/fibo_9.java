static double fib(int n) {
    if (fib[n] != 0)
        return fib[n];
    if (n == 0)
        return 0;
    else if (n == 1)
        return 1;
    else
        fib[n] = fib(n - 1) + fib(n - 2);
    return (fib(n - 1) + fib(n - 2));
}

