public class Program {
    private static final String THREAD_COUNT = "--count=";
    public static final Object lockObj = new Object();

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

        NamedThread egg = new NamedThread(thCount, "Egg");
        NamedThread hen = new NamedThread(thCount, "Hen");
        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void monitor(String name) {
        System.out.println(name);
        synchronized (Program.lockObj) {
            Program.lockObj.notifyAll();
        }
    }
}
