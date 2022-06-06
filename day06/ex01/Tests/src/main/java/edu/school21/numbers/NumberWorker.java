package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        boolean isPrime = true;
        if (number < 2) {
            throw new IllegalNumberException("Illegal Number");
        }
        int i = 2;
        while (i * i <= number) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        return isPrime;
    }

    public int digitsSum(int number) {
        int result = 0;
        int mod = 10;

        while (number > 0) {
            result += number % mod;
            number /= mod;
        }
        return result;
    }

    public class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String message) {
            super(message);
        }
    }
}
