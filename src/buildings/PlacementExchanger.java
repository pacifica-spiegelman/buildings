package buildings;

import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;

public class PlacementExchanger {

    private static boolean changeableSpace(Space space1, Space space2){
        return space1.getArea() == space2.getArea() && space1.getRoom() == space1.getRoom();
    }

    private static boolean changeableFloor(Floor floor1, Floor floor2){
        return floor1.getSpaceArea() == floor2.getSpaceArea() && floor1.getSpaceRoom() == floor2.getSpaceRoom();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if(changeableSpace(floor1.getSpace(index1), floor2.getSpace(index2)) && index1 <= floor1.getSpaceRoom() && index2 <= floor2.getSpaceRoom()){
            Space space1 = floor1.getSpace(index1);
            Space space2 = floor2.getSpace(index2);
            floor1.setSpace(index1, space2);
            floor2.setSpace(index2, space1);
        }
        throw new InexchangeableSpacesException();
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if(changeableFloor(building1.getFloor(index1), building2.getFloor(index2)) && index1 <= building1.getFloorAmount() && index2 <= building2.getFloorAmount()){
            Floor floor1 = building1.getFloor(index1);
            Floor floor2 = building2.getFloor(index2);
            building1.setFloor(index1, floor2);
            building2.setFloor(index2, floor1);
        }
        throw new InexchangeableFloorsException();
    }
}
