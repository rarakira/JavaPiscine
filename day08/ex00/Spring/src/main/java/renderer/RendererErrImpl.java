package renderer;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String messageToPrint) {
        messageToPrint = preProcessor.process(messageToPrint);
        System.err.println(messageToPrint);
    }
}
