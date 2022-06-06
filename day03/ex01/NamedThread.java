public class NamedThread extends Thread {
    private int count;
    private String name;

    public NamedThread(int count, String name) {
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++) {
            Program.monitor(this.name);
            if (i + 1 == this.count) {
                break;
            }
            synchronized (Program.lockObj) {
                try {
                    Program.lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
