package List;

import buildings.Floor;
import buildings.office.OfficeFloor;

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
}
