package buildings.patterns;

import buildings.Floor;

import java.util.Comparator;

public class ComparatorFloor implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return Double.compare(o2.getSpaceArea(), o1.getSpaceArea());
    }
}
