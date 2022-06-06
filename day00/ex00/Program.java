public class Program {
    public static void main(String[] args) {
        int number = 999999;
        int result = 0;
        int mod = 10;

        result += number % mod;
        number /= 10;
        result += number % mod;
        number /= 10;
        result += number % mod;
        number /= 10;
        result += number % mod;
        number /= 10;
        result += number % mod;
        number /= 10;
        result += number % mod;
        System.out.println(result);
    }
}
