package exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException() {
    }

    public InvalidRoomsCountException(String s) {
        super(s);
    }

    public InvalidRoomsCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRoomsCountException(Throwable cause) {
        super(cause);
    }
}
