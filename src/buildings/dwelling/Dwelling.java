package buildings.dwelling;

import buildings.Building;
import buildings.Buildings;
import buildings.Floor;
import buildings.patterns.IteratorBuilding;
import buildings.Space;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable {
    private Floor[] dFloor;

    public Dwelling(int numOfFloor, int...flats){
        dFloor  = new Floor[numOfFloor];
        for (int i = 0; i < dFloor.length; i++) {
            dFloor[i] = new DwellingFloor(flats[i]);
        }
    }

    public Dwelling(Floor...dFloor){
        this.dFloor = dFloor;
    }

    @Override
    public int getFloorAmount() {
        return dFloor.length;
    }

    @Override
    public int getSpaceAmount() {
        int flats = 0;
        for (int i = 0; i < dFloor.length; i++) {
            flats += dFloor[i].getSpaceAmount();
        }
        return flats;
    }

    @Override
    public double getSpaceArea() {
        double areaFl = 0;
        for (int i = 0; i < dFloor.length; i++) {
            areaFl += dFloor[i].getSpaceArea();
        }
        return areaFl;
    }

    @Override
    public int getSpaceRoom() {
        int allRooms = 0;
        for (int i = 0; i < dFloor.length; i++) {
            allRooms += dFloor[i].getSpaceRoom();
        }
        return allRooms;
    }

    @Override
    public Floor[] getFloorArray() {
        return dFloor;
    }

    @Override
    public Floor getFloor(int number) {
        return dFloor[number];
    }

    @Override
    public void setFloor(int number, Floor newFloor) {
        dFloor[number] = newFloor;
    }

    @Override
    public Space getSpace(int number) {
        Space flatG = null;
        int numFlat = 0;
        for (Floor dwellingFloor : dFloor) {
            for (Space flat : dwellingFloor.getSpaceArray()) {
                if (numFlat == number)
                    flatG = flat;
                numFlat++;
            }
        }
        return flatG;
    }

    @Override
    public void setSpace(int number, Space newSpace) {
        int numFlat = 0;
        for (int i = 0; i < dFloor.length; i++) {
            for (int j = 0; j < dFloor[i].getSpaceArray().length; j++) {
                if(numFlat == number)
                    dFloor[i].setSpace(j, newSpace);
                numFlat++;
            }
        }
    }

    @Override
    public void addSpace(int number, Space newSpace) {
        int numFlat = 0;
        for (int i = 0; i < dFloor.length; i++) {
            for (int j = 0; j < dFloor[i].getSpaceArray().length; j++) {
                if(numFlat == number)
                    dFloor[i].addSpace(j, newSpace);
                numFlat++;
            }
        }
    }

    @Override
    public void deleteSpace(int number) {
        int numFlat = 0;
        for (int i = 0; i < dFloor.length; i++) {
            for (int j = 0; j < dFloor[i].getSpaceArray().length; j++) {
                if(numFlat == number)
                    dFloor[i].deleteSpace(j);
                numFlat++;
            }
        }
    }

    @Override
    public Space getBestSpace(){ // получения самой большой по площади квартиры дома
        return getSortArray()[0];
    }

    @Override
    public Space[] getSortArray() {
        Space[] spaceSort = new Space[getSpaceAmount()];
        for (int i = 0, k = 0; i < getFloorAmount(); i++) {
            for (int j = 0; j < getFloor(i).getSpaceAmount(); j++) {
                spaceSort[k] = getFloor(i).getSpace(j);
                k++;
            }
        }
        return Buildings.sort(spaceSort, (o1, o2) -> -Double.compare(((Space) o1).getArea(), ((Space) o2).getArea()));
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        for(Floor floor: dFloor) {
            stringFloor.append(floor.toString());
        }
        return ("Dwelling " + "(" + getFloorAmount() + ", " + stringFloor + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(obj instanceof Dwelling && ((Dwelling) obj).getFloorAmount() == getFloorAmount()){
            flag = true;
            for(Floor floor: dFloor){
                if(!floor.equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer floorAmount = getFloorAmount();
        for (int i = 0; i < floorAmount; i++) {
            result += floorAmount.byteValue() ^ floorAmount.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Floor[] array = new Floor[getFloorAmount()];
        for (int i = 0; i < getFloorAmount(); i++) {
            array[i] = (Floor) getFloor(i).clone();
        }
        return new Dwelling(array);
    }

    @Override
    public Iterator<Floor> iterator() {
        return new IteratorBuilding(this);
    }
}
