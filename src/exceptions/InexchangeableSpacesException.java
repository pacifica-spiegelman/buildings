package exceptions;

public class InexchangeableSpacesException extends Exception {
    public InexchangeableSpacesException() {
    }

    public InexchangeableSpacesException(String message) {
        super(message);
    }

    public InexchangeableSpacesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InexchangeableSpacesException(Throwable cause) {
        super(cause);
    }

    public InexchangeableSpacesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
