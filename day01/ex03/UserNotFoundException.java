package ex03;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errMessage) {
        super(errMessage);
    }
}
