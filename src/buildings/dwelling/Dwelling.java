package buildings.dwelling;

import buildings.Building;
import buildings.Floor;
import buildings.patterns.IteratorBuilding;
import buildings.Space;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable {
    private Floor[] dFloor;

    public Dwelling(int numOfFloor, int[] flats){
        dFloor  = new Floor[numOfFloor];
        for (int i = 0; i < dFloor.length; i++) {
            dFloor[i] = new DwellingFloor(flats[i]);
        }
    }

    public Dwelling(Floor[] dFloor){
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
    public int getSpaceArea() {
        int areaFl = 0;
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

    public Space getBestSpace(){ // получения самой большой по площади квартиры дома
        return getSortArray()[0];
    }

    @Override
    public Space[] getSortArray() {
        Space[] flatSort = new Space[getSpaceRoom()];
        int start = 0;
        for (int j = 0, k = 0; j < dFloor.length; j++) {
            for (int i = 0; i < dFloor[j].getSpaceArray().length; i++) {
                flatSort[k] = dFloor[j].getSpaceArray()[i];
                k++;
            }
        }
        for (int i = 0; i < flatSort.length; i++) {
            double maxArea = 0;
            for (int j = i; j < flatSort.length; j++) {
                if(flatSort[j].getArea() > maxArea){
                    maxArea = flatSort[j].getArea();
                    Space flat = flatSort[i];
                    flatSort[i] = flatSort[j];
                    flatSort[j] = flat;
                }
            }
        }
        return flatSort;
    }

    @Override
    public String toString() {
        StringBuffer stringFloor = new StringBuffer();
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
