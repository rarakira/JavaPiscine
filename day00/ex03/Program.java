import java.util.Scanner;

public class Program {
    private static final int MAX_WEEKS = 18;
    private static final int NUM_OF_MARKS = 5;

    public static void main(String[] args) {
        long stats = 0;
        int week = 1;
        Scanner scanner = new Scanner(System.in);
        String clientInput = scanner.nextLine();

        while (!clientInput.equals("42")) {
            if (!clientInput.equals("Week " + week)) {
                exitOnError(scanner);
            }
            stats += insertNumber(getLowestGrade(scanner), week);
            week++;
            if (week > MAX_WEEKS) {
                break;
            }
            clientInput = scanner.nextLine();
        }
        scanner.close();
//        System.out.println(stats);
        printStats(week, stats);
    }

    private static int getLowestGrade(Scanner scanner) {
        int grade = 0;
        int res = 11;

        for (int i = 0; i < NUM_OF_MARKS; i++) {
            if (scanner.hasNextInt()) {
                grade = scanner.nextInt();
            } else {
                exitOnError(scanner);
            }
            if (grade < 1 || grade > 9) {
                exitOnError(scanner);
            }
            if (grade < res) {
                res = grade;
            }
        }
        scanner.nextLine();
        return (res);
    }

    private static void exitOnError(Scanner scanner) {
        scanner.close();
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    private static long insertNumber(int res, int week) {
        long mod = 1;

        for (int i = 1; i < week; i++) {
            mod *= 10;
        }
        return (res * mod);
    }

    private static void printStats(int week, long stats) {
        int res;

        for (int i = 1; i < week; i++) {
            System.out.print("Week " + i + " ");
            res = getResult(i, stats);
            while (res > 0) {
                System.out.print("=");
                res--;
            }
            System.out.println(">");
        }
    }

    private static int getResult(int week, long stats) {
        long mod = 1;
        long res;

        for (int i = 1; i < week; i++) {
            mod *= 10;
        }
        res = stats / mod;
        return ((int) (res % 10));
    }
}
