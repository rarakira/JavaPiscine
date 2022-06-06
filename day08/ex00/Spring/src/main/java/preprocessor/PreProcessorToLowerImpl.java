package preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String process(String messageToPrint) {
        return messageToPrint.toLowerCase();
    }
}
