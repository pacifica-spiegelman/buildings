package buildings.dwelling;

import buildings.Space;

public class Flat implements Space, Cloneable{
    private double area;
    private int room;
    private static final double AREA = 50;
    private static final int ROOM = 2;


    public Flat(){
        this.area = AREA;
        this.room = ROOM;
    }
    public Flat(double area){
        this.area = area;
        this.room = ROOM;
    }

    public Flat(double area, int flats){
        this.area = area;
        this.room = flats;
    }

    public int getRoom(){
        return room;
    }

    public void setRoom(int roomUs){
        room = roomUs;
    }

    public double getArea(){
        return area;
    }

    public void setArea(int areaUs){
        area = areaUs;
    }

    public String toString(){
        return ("Flat " + "(" + room + ", " + area + ")");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Flat && ((Flat) obj).area == area && ((Flat) obj).room == room;
    }

    @Override
    public int hashCode() {
        Integer room = this.room;
        Double area = this.area;
        return room.byteValue() ^ area.byteValue();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public int compareTo(Space o) {
        return Double.compare(area, o.getArea());
    }
}
