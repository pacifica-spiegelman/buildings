package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private int stars;
    private static final int STAR = 1;

    public HotelFloor(int numFlat) {
        super(numFlat);
        this.stars = STAR;
    }

    public HotelFloor(Space[] flats) {
        super(flats);
        this.stars = STAR;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuffer stringFlats = new StringBuffer();
        for(Space flat: getSpaceArray()) {
            stringFlats.append(flat.toString());
        }
        return ("HotelFloor " + "(" + getStars() + ", " + getSpaceAmount() + ", " + stringFlats + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(obj instanceof DwellingFloor && ((DwellingFloor) obj).getSpaceAmount() == getSpaceAmount()){
            flag = true;
            for(Space flat: getSpaceArray()){
                if(!flat.equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer spaceAmount = getSpaceAmount();
        for (int i = 0; i < spaceAmount; i++) {
            result += spaceAmount.byteValue() ^ spaceAmount.hashCode();
        }
        return result;
    }
}
