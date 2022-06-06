import static java.lang.Thread.sleep;

public class Program {
    private static final String THREAD_COUNT = "--count=";

    public static void main(String[] args) {
        int thCount = 0;

        if (args.length != 0 && args[0].startsWith(THREAD_COUNT)) {
            try {
                thCount = Integer.parseInt(args[0].substring(THREAD_COUNT.length()));
            } catch (NumberFormatException e) {
                System.err.println("The argument should contain number. Example: --count=5");
                System.exit(-1);
            }
        } else {
            System.err.println("The program takes argument: --count=N");
            System.exit(-1);
        }

        int finalThCount = thCount;
        Runnable egg = () -> {
            for (int i = 0; i < finalThCount; i++) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {}
                System.out.println("Egg");
            }
        };
        Runnable hen = () -> {
            for (int i = 0; i < finalThCount; i++) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {}
                System.out.println("Hen");
            }
        };

        Thread first = new Thread(egg);
        Thread second = new Thread(hen);
        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < thCount; i++) {
            System.out.println("Human");
        }

    }
}
