package buildings.patterns;

import buildings.Space;

import java.util.Comparator;

public class ComparatorSpace implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        return Integer.compare(o2.getRoom(), o1.getRoom());
    }
}
