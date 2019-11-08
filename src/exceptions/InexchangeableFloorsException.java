package exceptions;

public class InexchangeableFloorsException extends Exception {
    public InexchangeableFloorsException() {
    }

    public InexchangeableFloorsException(String message) {
        super(message);
    }

    public InexchangeableFloorsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InexchangeableFloorsException(Throwable cause) {
        super(cause);
    }

    public InexchangeableFloorsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
