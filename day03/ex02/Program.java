import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final String THREAD_COUNT = "--threadsCount=";
    private static final String ARRAY_SIZE = "--arraySize=";

    public static void main(String[] args) {
        int thCount = 0;
        int arrSize = 0;

        if (args.length == 2 && args[0].startsWith(ARRAY_SIZE) && args[1].startsWith(THREAD_COUNT)) {
            try {
                arrSize = Integer.parseInt(args[0].substring(ARRAY_SIZE.length()));
                thCount = Integer.parseInt(args[1].substring(THREAD_COUNT.length()));
                if (arrSize == 0 || thCount == 0) {
                    System.err.println("The argument values should be greater than 0");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                System.err.println("The argument should contain numbers. Example: --arraySize=13 --threadsCount=3");
                System.exit(-1);
            }
        } else {
            System.err.println("The program takes argument: --arraySize=N --threadsCount=M");
            System.exit(-1);
        }
        List<Integer> intList = new ArrayList<>(arrSize);
        for (int i = 0; i < arrSize; i++) {
            int n = (int) (1000 - (Math.random() * 2000));
            intList.add(n);
        }

        System.out.println("Sum: " + calculateMain(intList));

        if (thCount > arrSize) {
            thCount = arrSize;
        }

        int range = arrSize / thCount;
        List<Thread> threads = new ArrayList<>(thCount);
        int start = 0;
        int end = 0;
        for (int i = 0; i < thCount - 1; i++) {
            start = i * range;
            end = (i + 1) * range;
            threads.add(new CalculatorThreads(intList.subList(start, end), start, end - 1));
        }

        start = (thCount - 1) * range;
        threads.add(new CalculatorThreads(intList.subList(start, arrSize), start, arrSize - 1));

        for (Thread thread : threads) { thread.start(); }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum by threads: " + CalculatorThreads.getFinalSum());
    }

    private static int calculateMain(List<Integer> list) {
        int tmp = 0;
        for (int x : list)
            tmp += x;
        return tmp;
    }
}