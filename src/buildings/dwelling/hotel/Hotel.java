package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class Hotel extends Dwelling {

    public Hotel(int numOfFloor, int...flats) {
        super(numOfFloor, flats);
    }

    public Hotel(Floor...dFloor) {
        super(dFloor);
    }

    public int getStars(){
        int max = 0;
        for (int i = 0; i < getFloorAmount(); i++) {
            if(getFloor(i) instanceof HotelFloor){
                if(((HotelFloor) getFloor(i)).getStars() > max)
                    max = ((HotelFloor) getFloor(i)).getStars();
            }

        }
        return max;
    }

    @Override
    public Space getBestSpace() {
        Space flat = null;
        double max = 0;
        double[] coeff = {0.25, 0.5, 1, 1.25, 1.5};
        for (int i = 0; i < getFloorAmount(); i++) {
            for (int j = 0; j < getFloor(i).getSpaceAmount(); j++) {
                if (getFloor(i).getSpace(j).getArea() * coeff[((HotelFloor)getFloor(i)).getStars() - 1] > max) {
                    max = getFloor(i).getSpace(j).getArea() * coeff[((HotelFloor)getFloor(i)).getStars() - 1];
                    flat = getFloor(i).getSpace(j);
                }
            }
        }
        return flat;
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        for(Floor floor: getFloorArray()) {
            stringFloor.append(floor.toString());
        }
        return ("Hotel " + "(" + getFloorAmount() + ", " + stringFloor + ")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(obj instanceof Dwelling && ((Dwelling) obj).getFloorAmount() == getFloorAmount()){
            flag = true;
            for(Floor floor: getFloorArray()){
                if(!floor.equals(obj))
                    return false;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Integer floorAmount = getFloorAmount();
        for (int i = 0; i < floorAmount; i++) {
            result += floorAmount.byteValue() ^ floorAmount.hashCode();
        }
        return result;
    }
}
