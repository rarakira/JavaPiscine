package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String messageToPrint) {
        return messageToPrint.toUpperCase();
    }
}
