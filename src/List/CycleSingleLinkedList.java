package List;

import buildings.office.Office;
import buildings.Space;

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
}
