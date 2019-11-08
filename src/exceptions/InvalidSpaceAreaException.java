package exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
    }

    public InvalidSpaceAreaException(String s) {
        super(s);
    }

    public InvalidSpaceAreaException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSpaceAreaException(Throwable cause) {
        super(cause);
    }
}
