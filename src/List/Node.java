package List;


import buildings.Space;

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
