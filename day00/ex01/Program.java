import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        boolean prime = true;
        int number = 0;
        int operations = 1;
        System.out.print("-> ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            exitOnError(scanner);
        }
        if (number < 2) {
            exitOnError(scanner);
        } else {
            int i = 2;
            while (i * i <= number) {
                if (number % i == 0) {
                    prime = false;
                    break;
                }
                operations++;
                i++;
            }
        }
        System.out.println(prime + " " + operations);
        scanner.close();
    }

    private static void exitOnError(Scanner scanner) {
        scanner.close();
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}
