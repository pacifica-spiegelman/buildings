package buildings;

public interface Building extends Iterable<Floor> {
    int getFloorAmount();
    int getSpaceAmount();
    double getSpaceArea();
    int getSpaceRoom();
    Floor[] getFloorArray();
    Floor getFloor(int number);
    void setFloor(int number, Floor newFloor);
    Space getSpace(int number);
    void setSpace(int number, Space newSpace);
    void addSpace(int number, Space newSpace);
    void deleteSpace(int number);
    Space getBestSpace();
    Space[] getSortArray();
    Object clone() throws CloneNotSupportedException;
}
