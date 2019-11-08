package buildings;

public interface Space extends Comparable<Space>{
    int getRoom();
    void setRoom(int room);
    double getArea();
    void setArea(int area);
    Object clone() throws CloneNotSupportedException;
    int compareTo(Space o);
}
