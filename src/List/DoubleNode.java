package List;

import buildings.Floor;

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
