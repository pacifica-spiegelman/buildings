package buildings.patterns;

import buildings.Floor;
import buildings.Space;

public class IteratorFloor implements java.util.Iterator<Space> {
    private int index;
    private Floor floor;

    public IteratorFloor(Floor floor){
        index = 0;
        this.floor = floor;
    }

    @Override
    public boolean hasNext() {
        return floor.getSpace(index + 1) != null;
    }

    @Override
    public Space next() {
        Space next = null;
        if(hasNext()) {
            next = floor.getSpace(index + 1);
            index++;
        }
        return next;
    }
}
