package printer;

import renderer.Renderer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

public class PrinterWithDateTimeImpl implements Printer {
    private LocalDateTime timeStamp;
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.timeStamp = now();
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.print(this.timeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + text);
    }
}
