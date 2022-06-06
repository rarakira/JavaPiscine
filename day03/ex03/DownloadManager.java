import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;

public class DownloadManager {
    private static Queue<String> urls;
    private static int fileNumber = 0;

    public static void initDownloadManager(Queue<String> queue) {
        urls = queue;
    }

    public static synchronized String getNextUrl() {
        String url;
        if ((url = urls.poll()) != null)
            return url;
        return null;
    }

    public static void downloadFile(String url) {
        try {
            URL link = new URL(url);
            Path downloadFile = Paths.get(url);
            if (Files.exists(Paths.get("downloads/" + downloadFile.getFileName()))) {
                System.out.println(downloadFile.getFileName() + " was already downloaded");
                return;
            }
            int fileNum;
            synchronized (DownloadManager.class) {
                fileNum = fileNumber++;
            }
            System.out.println(Thread.currentThread().getName() + " start download file number " + fileNum);
            InputStream input = link.openStream();
            Files.copy(input, Paths.get("downloads/" + downloadFile.getFileName()));
            System.out.println(Thread.currentThread().getName() + " finish download file number " + fileNum);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
