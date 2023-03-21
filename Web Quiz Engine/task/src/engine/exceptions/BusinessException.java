package engine.exceptions;

public class BusinessException extends Exception {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
