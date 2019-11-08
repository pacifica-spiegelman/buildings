package exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException() {
    }

    public FloorIndexOutOfBoundsException(String s) {
        super(s);
    }

    public FloorIndexOutOfBoundsException(int index) {
        super(index);
    }
}
