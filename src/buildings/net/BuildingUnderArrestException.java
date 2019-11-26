package buildings.net;

public class BuildingUnderArrestException extends Exception {
    public BuildingUnderArrestException() {
    }

    public BuildingUnderArrestException(String message) {
        super(message);
    }

    public BuildingUnderArrestException(String message, Throwable cause) {
        super(message, cause);
    }
}
