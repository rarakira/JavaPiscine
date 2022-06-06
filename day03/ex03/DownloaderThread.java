public class DownloaderThread extends Thread {
    @Override
    public void run() {
        String fileUrl;

        while ((fileUrl = DownloadManager.getNextUrl()) != null)
            DownloadManager.downloadFile(fileUrl);
    }
}
