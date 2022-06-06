import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int number;
        int coffee = 0;
        boolean loop = true;
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
            } else {
                System.err.println("Illegal Argument");
                scanner.nextLine();
                continue;
            }
            if (number == 42) {
                loop = false;
                break;
            }
            number = getSumOfDigits(number);
            coffee += isCoffeeRequest(number);
        }
        System.out.println("Count of coffee-request - " + coffee);
        scanner.close();
    }

    private static int getSumOfDigits(int number) {
        int result = 0;

        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return (result);
    }

    private static int isCoffeeRequest(int number) {
        boolean prime = true;

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                prime = false;
                break;
            }
        }
        if (prime) {
            return (1);
        }
        return (0);
    }
}

