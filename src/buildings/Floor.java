package buildings;

public interface Floor extends Iterable<Space>, Comparable<Floor>{
    int getSpaceAmount();
    int getSpaceArea();
    int getSpaceRoom();
    Space[] getSpaceArray();
    Space getSpace(int number);
    void setSpace(int number, Space newSpace);
    void addSpace(int number, Space newSpace);
    void deleteSpace(int number);
    Space getBestSpace();
    Object clone() throws CloneNotSupportedException;
    java.util.Iterator iterator();
    int compareTo(Floor o);
}