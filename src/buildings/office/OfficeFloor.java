package buildings.office;

import buildings.Floor;
import buildings.Space;
import buildings.patterns.IteratorFloor;

import java.util.Iterator;

public class OfficeFloor implements Floor {
    private CycleSingleLinkedList office;

    private CycleSingleLinkedList.Node getNode(int number){
        return office.getNode(number);
    }

    private void addNode(int number, Space info){
        office.addNode(number, info);
    }

    private void deleteNode(int number){
        office.deleteNode(number);
    }

    public OfficeFloor(int amount){
        office = new CycleSingleLinkedList(amount);
        for (int i = 0; i < amount; i++) {
            office.addNodeLast(new Office());
        }
    }

    public OfficeFloor(Space[] offices){
        office = new CycleSingleLinkedList();
        for (Space value : offices) {
            office.addNodeLast(value);
        }
    }

    @Override
    public int getSpaceAmount() {
        return office.getAmount();
    }

    @Override
    public double getSpaceArea() {
        double area = 0;
        IteratorFloor iterator = (IteratorFloor) iterator();
        for (int i = 1; i <= getSpaceAmount(); i++) {
            area += iterator.next().getArea();
        }
        return area;
    }

    @Override
    public int getSpaceRoom() {
        int room = 0;
        for (int i = 1; i <= getSpaceAmount(); i++) {
            room += getNode(i).info.getRoom();
        }
        return room;
    }

    @Override
    public Space[] getSpaceArray() {
        Space[] offices = new Space[getSpaceAmount()];
        for (int i = 1; i <= getSpaceAmount(); i++) {
            offices[i] = getNode(i).info;
        }
        return offices;
    }

    @Override
    public Space getSpace(int number) {
        return getNode(number).info;
    }

    @Override
    public void setSpace(int number, Space newSpace) {
        getNode(number).info = newSpace;
    }

    @Override
    public void addSpace(int number, Space newSpace) {
        addNode(number, newSpace);
    }

    @Override
    public void deleteSpace(int number) {
        deleteNode(number);
    }

    public Space getBestSpace(){
        double area = 0;
        Space bestOffice = null;
        for (int i = 1; i <= getSpaceAmount(); i++) {
            Space office = getNode(i).info;
            if(office.getArea() > area){
                area = office.getArea();
                bestOffice = office;
            }
        }
        return bestOffice;
    }

    public String toString(){
        StringBuffer stringFlats = new StringBuffer();
        for (int i = 1; i <= getSpaceAmount(); i++) {
            stringFlats.append(getNode(i).info.toString());
        }
        return ("OfficeFloor " + "(" + getSpaceAmount() + ", " + stringFlats + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj instanceof OfficeFloor && ((OfficeFloor) obj).getSpaceAmount() == getSpaceAmount()) {
            flag = true;
            for (int i = 1; i <= getSpaceAmount(); i++) {
                if(!getSpace(i).equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer spaceAmount = getSpaceAmount();
        for (int i = 1; i <= spaceAmount; i++) {
            result += spaceAmount.byteValue() ^ spaceAmount.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Space[] array = new Space[getSpaceAmount()];
        for (int i = 1; i <= getSpaceAmount(); i++) {
            array[i] = (Space) getSpace(i).clone();
        }
        return new OfficeFloor(array);
    }

    @Override
    public Iterator<Space> iterator() {
        return new IteratorFloor(this);
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(getSpaceAmount(), o.getSpaceAmount());
    }

    public class CycleSingleLinkedList {
        private Node head;
        private int amount;

        public CycleSingleLinkedList(){
            head = new Node(new Office());
            head.next = head;
            amount = 0;
        }

        public CycleSingleLinkedList(int amount){
            this();
            this.amount = amount;
        }


        public int getAmount() {
            return amount;
        }

        public Node getNode(int number){
            if(head != null) {
                Node p = head.next;
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


        public void addNodeLast(Space info){
            if(head != null){
                Node p = head;
                while (p.next != head){
                    p = p.next;
                }
                p.next = new Node(info,head);
            }
        }

        public void addNode(int number, Space info){
            if(head != null){
                Node p  = head.next;
                while (p != head && number > 1){
                    p = p.next;
                    number--;
                }
                if(p != head) {
                    p.next = new Node(info, p.next);
                    this.amount++;
                }
            }
        }

        public void deleteNode(int number){
            if(head != null){
                Node p = head.next;
                while (p != head && number > 1){
                    p = p.next;
                    number--;
                }
                if(p != head) {
                    p.next = p.next.next;
                    this.amount--;
                }
            }
        }

        public class Node {
            public Node next;
            public Space info;

            public Node(Space info){
                this.info = info; // maybe new buildings.office.Office()
                next = null;
            }

            public Node(Space info, Node next){
                this.info = info;
                this.next = next;
            }

        }
    }
}
