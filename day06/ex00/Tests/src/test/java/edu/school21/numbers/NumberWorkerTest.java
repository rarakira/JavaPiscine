package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker nw;

    @BeforeEach
    void beforeEachMethod() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 139, 683, 1217, 2939, 4493, 5683})
    void  isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 133, 813, 1203, 2115, 4521, 7917})
    void  isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 0})
    void  isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrowsExactly(NumberWorker.IllegalNumberException.class, () -> nw.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void digitsSumTest(int x, int res) {
        Assertions.assertEquals(nw.digitsSum(x), res);
    }

}
