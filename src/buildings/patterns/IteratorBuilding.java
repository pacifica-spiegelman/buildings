package buildings.patterns;

import buildings.Building;
import buildings.Floor;

import java.util.Iterator;

public class IteratorBuilding implements Iterator<Floor> {
    private int index;
    private Building building;

    public IteratorBuilding(Building building){
        index = 0;
        this.building = building;
    }

    @Override
    public boolean hasNext() {
        return building.getFloor(index+1) != null;
    }

    @Override
    public Floor next() {
        Floor next = null;
        if(hasNext()){
            next = building.getFloor(index+1);
            index++;
        }
        return next;
    }
}
