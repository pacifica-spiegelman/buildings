package buildings.office;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.patterns.IteratorBuilding;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable {
    private CycleDoubleLinkedList floor;

    private CycleDoubleLinkedList.DoubleNode getDoubleNode(int number){
        return floor.getDoubleNode(number);
    }

    private void addDoubleNode(int number, Floor info) {
        floor.addDoubleNode(number, info);
    }

    private void deleteDoubleNode(int number){
        floor.deleteDoubleNode(number);
    }

    public OfficeBuilding(int amount, int... numOffices){
        floor = new CycleDoubleLinkedList(amount);
        for (int i = 0; i < amount ; i++) {
            floor.addDoubleNodeLast(new OfficeFloor(numOffices[i]));
        }
    }

    public OfficeBuilding(Floor...floors){
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
    public double getSpaceArea() {
        double area = 0;
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

    @Override
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

    @Override
    public Space getBestSpace(){
        return getSortArray()[0];
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        for (int i = 1; i <= getFloorAmount(); i++) {
            stringFloor.append(getDoubleNode(i).info.toString());
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

    public class CycleDoubleLinkedList {
        private DoubleNode head;
        private int amount;

        public CycleDoubleLinkedList() {
            head = new DoubleNode(new OfficeFloor(0));
            head.next = head;
            head.prev = head;
            amount = 0;
        }

        public CycleDoubleLinkedList(int length) {
            this();
            this.amount = length;
        }

        public void addDoubleNodeLast(Floor floor) {
            if (head != null) {
                DoubleNode p = new DoubleNode(floor, head, head.prev);
                head.prev.next = p;
                head.prev = p;
            }
        }


        public int getAmount() {
            return amount;
        }

        public DoubleNode getDoubleNode(int number) {
            if (head != null) {
                DoubleNode p = head.next;
                int i = 1;
                while (p != head && i != number) {
                    p = p.next;
                    i++;
                }
                if (p != head)
                    return p;
            }
            return null;
        }

        public void addDoubleNode(int number, Floor floor) {
            if (head != null) {
                DoubleNode p = head.next;
                while (p != head && number > 1) {
                    p = p.next;
                    number--;
                }
                if (p != head) {
                    DoubleNode q = new DoubleNode(floor, p.next, p);
                    p.next = q;
                    q.next.prev = q;
                    this.amount++;
                }
            } else {
                System.out.println("Ошибка при добавлении элемента!");
            }
        }

        public void deleteDoubleNode(int number) {
            if (head != null) {
                DoubleNode p = head.next;
                while (p != head && number > 1) {
                    p = p.next;
                    number--;
                }
                if (p != head) {
                    p.next.prev = p.prev;
                    p.prev.next = p.next;
                    this.amount--;
                }
            } else {
                System.out.println("Ошибка при удалении элемента!");
            }
        }

        public class DoubleNode {
            public Floor info;
            public DoubleNode next;
            public DoubleNode prev;

            public DoubleNode(Floor info){
                this.info = info;
                next = null;
                prev = null;
            }

            public DoubleNode(Floor info, DoubleNode next, DoubleNode prev){
                this.info = info;
                this.next = next;
                this.prev = prev;
            }
        }
    }
}
