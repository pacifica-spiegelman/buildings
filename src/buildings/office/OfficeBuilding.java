package buildings.office;

import List.CycleDoubleLinkedList;
import List.DoubleNode;
import buildings.Building;
import buildings.Floor;
import buildings.patterns.IteratorBuilding;
import buildings.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable {
    private CycleDoubleLinkedList floor;

    private DoubleNode getDoubleNode(int number){
        return floor.getDoubleNode(number);
    }

    private void addDoubleNode(int number, Floor info) {
        floor.addDoubleNode(number, info);
    }

    private void deleteDoubleNode(int number){
        floor.deleteDoubleNode(number);
    }

    public OfficeBuilding(int amount, int[] numOffices){
        floor = new CycleDoubleLinkedList(amount);
        for (int i = 0; i < amount ; i++) {
            floor.addDoubleNodeLast(new OfficeFloor(numOffices[i]));
        }
    }

    public OfficeBuilding(Floor[] floors){
        floor = new CycleDoubleLinkedList();
        for(Floor value: floors){
            floor.addDoubleNodeLast(value);
        }
    }

    @Override
    public int getFloorAmount() {
        return floor.getAmount();
    }

    @Override
    public int getSpaceAmount() {
        int offices = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            offices += getDoubleNode(i).info.getSpaceAmount();
        }
        return offices;
    }

    @Override
    public int getSpaceArea() {
        int area = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            area += getDoubleNode(i).info.getSpaceArea();
        }
        return area;
    }

    @Override
    public int getSpaceRoom() {
        int rooms = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            rooms += getDoubleNode(i).info.getSpaceRoom();
        }
        return rooms;
    }

    @Override
    public Floor[] getFloorArray() {
        Floor[] officeFloors = new Floor[getFloorAmount()];
        for (int i = 1; i <= getFloorAmount(); i++) {
            officeFloors[i] = getDoubleNode(i).info;
        }
        return officeFloors;
    }

    @Override
    public Floor getFloor(int number) {
        return getDoubleNode(number).info;
    }

    @Override
    public void setFloor(int number, Floor newFloor) {
        getDoubleNode(number).info = newFloor;
    }

    @Override
    public Space getSpace(int number) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        int numOffice = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            for (int j = 1; j <= getDoubleNode(i).info.getSpaceAmount(); j++) {
                if(numOffice == number)
                    return getDoubleNode(i).info.getSpace(j);
                numOffice++;
            }
        }
        return null;
    }

    @Override
    public void setSpace(int number, Space newSpace) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        int numOffice = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            for (int j = 1; j <= getDoubleNode(i).info.getSpaceAmount(); j++) {
                if(numOffice == number)
                    getDoubleNode(i).info.setSpace(j, newSpace);
                numOffice++;
            }
        }
    }

    @Override
    public void addSpace(int number, Space newSpace) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        int numOffice = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            for (int j = 1; j <= getDoubleNode(i).info.getSpaceAmount(); j++) {
                if(numOffice == number)
                    getDoubleNode(i).info.addSpace(j, newSpace);
                numOffice++;
            }
        }
    }

    @Override
    public void deleteSpace(int number) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        int numOffice = 0;
        for (int i = 1; i <= getFloorAmount(); i++) {
            for (int j = 1; j <= getDoubleNode(i).info.getSpaceAmount(); j++) {
                if(numOffice == number)
                    getDoubleNode(i).info.deleteSpace(j);
                numOffice++;
            }
        }
    }

    public Space[] getSortArray(){
        Space[] spaces = new Space[getSpaceAmount()];
        for (int i = 1, k = 0; i <= getFloorAmount(); i++) {
            for (int j = 1; j <= getDoubleNode(i).info.getSpaceAmount(); j++) {
                spaces[k] = getDoubleNode(i).info.getSpace(j);
                k++;
            }
        }
        for (int i = 0; i < spaces.length; i++) {
            double maxArea = 0;
            for (int j = i; j < spaces.length; j++) {
                if (spaces[j].getArea() > maxArea) {
                    maxArea = spaces[j].getArea();
                    Space flat = spaces[i];
                    spaces[i] = spaces[j];
                    spaces[j] = flat;
                }
            }
        }
        return spaces;
    }

    public Space getBestSpace(){
        return getSortArray()[0];
    }

    @Override
    public String toString() {
        StringBuffer stringFloor = new StringBuffer();
        for (int i = 1; i <= getFloorAmount(); i++) {
            stringFloor.append(getDoubleNode(i).toString());
        }
        return ("OfficeBuilding " + "(" + getFloorAmount() + ", " + stringFloor + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj instanceof OfficeBuilding && ((OfficeBuilding) obj).getFloorAmount() == getFloorAmount()) {
            flag = true;
            for (int i = 1; i <= getFloorAmount(); i++) {
                if(!getFloor(i).equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer floorAmount = getFloorAmount();
        for (int i = 1; i <= floorAmount; i++) {
            result += floorAmount.byteValue() ^ floorAmount.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Floor[] array = new Floor[getFloorAmount()];
        for (int i = 1; i <= getFloorAmount(); i++) {
            array[i] = (Floor) getFloor(i).clone();
        }
        return new OfficeBuilding(array);
    }

    @Override
    public Iterator<Floor> iterator() {
        return new IteratorBuilding(this);
    }
}
