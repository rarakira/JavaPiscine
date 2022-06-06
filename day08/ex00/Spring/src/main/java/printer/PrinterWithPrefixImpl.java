package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private String prefix;
    private Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.prefix = "";
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.print(this.prefix + text);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix + " ";
    }
}
