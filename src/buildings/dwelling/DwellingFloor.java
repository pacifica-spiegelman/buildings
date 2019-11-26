package buildings.dwelling;

import buildings.Floor;
import buildings.patterns.IteratorFloor;
import buildings.Space;

import java.util.Iterator;

public class DwellingFloor implements Floor {
    private Space[] flats;

    public DwellingFloor(int numFlat){
        flats = new Space[numFlat];
        for (int i = 0; i < flats.length; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flats){
        this.flats = flats;
    }

    @Override
    public int getSpaceAmount() {
        return flats.length;
    }

    @Override
    public double getSpaceArea() {
        double area = 0;
        IteratorFloor iterator = (IteratorFloor) iterator();
        for(Space space: flats){
            area += space.getArea();
        }
        return area;
    }

    @Override
    public int getSpaceRoom() {
        int room = 0;
        for (Space space: flats) {
            room += space.getRoom();
        }
        return room;
    }

    @Override
    public Space[] getSpaceArray() {
        return flats;
    }

    @Override
    public Space getSpace(int number) {
        return flats[number];
    }

    @Override
    public void setSpace(int number, Space newSpace) {
        flats[number] = newSpace;
    }

    @Override
    public void addSpace(int number, Space newSpace) {
        Space[] flat = new Space[flats.length+1];
        if(number <= flats.length){
            for (int i = 0; i < flat.length; i++) {
                if(i < number)
                    flat[i] = flats[i];
                else if(i == number)
                    flat[i] = newSpace;
                else
                    flat[i] = flats[i-1];
            }
        }
        else{
            for (int i = 0; i < flats.length; i++) {
                flat[i] = flats[i];
            }
            flat[number] = newSpace;
        }
        flats = flat;
    }

    @Override
    public void deleteSpace(int number) {
        Space[] flat = new Space[flats.length-1];
        for (int i = 0; i < flat.length; i++) {
            if(i < number)
                flat[i] = flats[i];
            else
                flat[i] = flats[i+1];
        }
        flats = flat;
    }

    public Space getBestSpace(){
        Space flat = null;
        double max = 0;
        for (Space space: flats) {
            if(space.getArea() > max) {
                max = space.getArea();
                flat = space;
            }
        }
        return flat;
    }

    public String toString(){
        StringBuffer stringFlats = new StringBuffer();
        for(Space flat: flats) {
            stringFlats.append(flat.toString());
        }
        return ("DwellingFloor " + "(" + getSpaceAmount() + ", " + stringFlats + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(obj instanceof DwellingFloor && ((DwellingFloor) obj).getSpaceAmount() == getSpaceAmount()){
            flag = true;
            for(Space flat: flats){
                if(!flat.equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer spaceAmount = getSpaceAmount();
        for (int i = 0; i < spaceAmount; i++) {
            result += spaceAmount.byteValue() ^ spaceAmount.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Space[] array = new Space[getSpaceAmount()];
        for (int i = 0; i < getSpaceAmount(); i++) {
            array[i] = (Space) getSpace(i).clone();
        }
        return new DwellingFloor(array);
    }

    @Override
    public Iterator<Space> iterator() {
        return new IteratorFloor(this);
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(getSpaceAmount(), o.getSpaceAmount());
    }
}
