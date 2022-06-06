import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Program {
    private static final String THREAD_COUNT = "--count=";

    public static void main(String[] args) throws IOException {
        int thCount = getThreadsCount(args);
        BufferedReader reader = new BufferedReader(new FileReader("files_urls.txt"));
        Queue<String> urlsQueue = new LinkedList<>();

        String url;
        while ((url = reader.readLine()) != null) {
            urlsQueue.add(url);
        }
        reader.close();

        File directory = new File("downloads");
        if (! directory.exists()){
            directory.mkdir();
        }

        DownloadManager.initDownloadManager(urlsQueue);

        List<Thread> threads = new ArrayList<>(thCount);
        for (int i = 0 ; i < thCount; i++) {
            threads.add(new DownloaderThread());
        }

        System.out.println(">> STARTING DOWNLOADS...");
        for (Thread thread : threads) { thread.start(); }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(">> DOWNLOADS ARE FINISHED!");

    }

    private static int getThreadsCount(String[] args) {
        int count = -1;
        if (args.length != 0 && args[0].startsWith(THREAD_COUNT)) {
            try {
                count = Integer.parseInt(args[0].substring(THREAD_COUNT.length()));
            } catch (NumberFormatException e) {
                System.err.println("The argument should contain number. Example: --count=5");
                System.exit(-1);
            }
        } else {
            System.err.println("The program takes argument: --count=N");
            System.exit(-1);
        }
        return count;
    }
}
