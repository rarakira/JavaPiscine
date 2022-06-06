import java.util.List;

public class CalculatorThreads extends Thread {
    private static int finalSum = 0;
    private int start;
    private int end;
    private int currentSum;

    @Override
    public void run() {
        addToSum(this.currentSum, this.start, this.end);
    }

    public CalculatorThreads(List<Integer> sublist, int start, int end) {
        this.start = start;
        this.end = end;
        this.currentSum = 0;
        for (int x : sublist) {
            this.currentSum += x;
        }
    }

    private static synchronized void addToSum(int currentSum, int start, int end) {
        System.out.println(Thread.currentThread().getName() +
                ": from " + start + " to " + end + " sum is " + currentSum);
        finalSum += currentSum;
    }

    public static int getFinalSum() {
        return finalSum;
    }
}
