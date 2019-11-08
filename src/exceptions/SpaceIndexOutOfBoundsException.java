package exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException() {
    }

    public SpaceIndexOutOfBoundsException(String s) {
        super(s);
    }

    public SpaceIndexOutOfBoundsException(int index) {
        super(index);
    }
}
