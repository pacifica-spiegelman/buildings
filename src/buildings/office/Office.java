package buildings.office;

import buildings.Space;

public class Office implements Space {
    private double area;
    private int room;
    private static final int ROOM = 1;
    private static final double AREA = 250;

    public Office(){
        this.area = AREA;
        this.room = ROOM;
    }

    public Office(double area){
        this.area = area;
        this.room = ROOM;
    }

    public Office(double area, int room){
        this.area = area;
        this.room = room;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public int getRoom() {
        return room;
    }

    @Override
    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString(){
        return ("Office " + "(" + room + ", " + area + ")");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Office && ((Office) obj).room == room && ((Office) obj).area == area;
    }

    @Override
    public int hashCode() {
        Integer room = this.room;
        Double area = this.area;
        return room.byteValue() ^ area.byteValue();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Space o) {
        return Double.compare(area, o.getArea());
    }
}
