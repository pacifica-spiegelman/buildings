package buildings;


import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.patterns.BuildingFactory;
import buildings.patterns.DwellingFactory;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;

public class Buildings {
    private static BuildingFactory buildingFactory = new DwellingFactory();


    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        if (building != null) {
            DataOutputStream dout = new DataOutputStream(out);
            dout.writeInt(building.getFloorAmount());
            dout.writeChars(" ");
            for (int i = 0; i < building.getFloorAmount(); i++) {
                dout.writeInt(building.getFloor(i).getSpaceRoom());
                dout.writeChars(" ");
                for (int j = 0; j < building.getFloor(i).getSpaceRoom(); j++) {
                    dout.writeInt(building.getFloor(i).getSpace(j).getRoom());
                    dout.writeChars(" ");
                    dout.writeDouble(building.getFloor(i).getSpace(j).getArea());
                    dout.writeChars(" ");
                }
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);
        Building result = null;
        if (din.available() > 0) {
            int floorsNum = din.readInt();
            din.skipBytes(2);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = din.readInt();
                din.skipBytes(2);
                Space[] spaces = new Space[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = din.readInt();
                    din.skipBytes(2);
                    int area = din.readInt();
                    din.skipBytes(2);
                    Space space = createSpace(area, roomNum);
                    spaces[j] = space;
                }
                floors[i] = createFloor(spaces);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        if (building != null) {
            int floorsCount = building.getFloorAmount();
            out.write(Integer.toString(floorsCount));
            out.write(" ");
            for (int i = 0; i < building.getFloorAmount(); i++) {
                out.write(Integer.toString(building.getFloor(i).getSpaceAmount()));
                out.write(" ");
                for (int j = 0; j < building.getFloor(i).getSpaceAmount(); j++) {
                    out.write(Integer.toString(building.getFloor(i).getSpace(j).getRoom()));
                    out.write(" ");
                    out.write(Double.toString(building.getFloor(i).getSpace(j).getArea()));
                    out.write(" ");
                }
            }
        }
    }

    public static Building readBuilding(Reader in) throws IOException {
        BufferedReader bin = new BufferedReader(in);
        Building result = null;
        if (bin.ready()) {
            String[] s = bin.readLine().split(" ");
            int count = 0;
            int floorsNum = Integer.parseInt(s[count++]);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = Integer.parseInt(s[count++]);
                Space[] spaces = new Office[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = Integer.parseInt(s[count++]);
                    int area = Integer.parseInt(s[count++]);
                    Space space = createSpace(area, roomNum);
                    spaces[j] = space;
                }
                floors[i] = createFloor(spaces);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        if (building != null) {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(building);
        }
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        Building result = null;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (Building) ois.readObject();
        return result;
    }

    public static void writeBuildingFormat(Building building, Writer out) throws IOException {
        if (building != null) {
            int floorsCount = building.getFloorAmount();
            out.write(String.format("Количество этажей: %d\n", floorsCount));
            for (int i = 0; i < building.getFloorAmount(); i++) {
                out.write(String.format("Количество помещений: %d\n", building.getFloor(i).getSpaceRoom()));
                for (int j = 0; j < building.getFloor(i).getSpaceRoom(); j++) {
                    out.write(String.format("Количество комнат в помещении: %d ", building.getFloor(i).getSpace(j).getRoom()));
                    out.write(String.format("Площадь помещения: %f\n", building.getFloor(i).getSpace(j).getArea()));
                }
            }
        }
    }

    public Building readBuilding(Scanner scanner) {
        Building result = null;
        if (scanner.hasNext()) {
            String[] s = scanner.next().split(" ");
            int count = 0;
            int floorsNum = Integer.parseInt(s[count++]);
            Floor[] floors = new Floor[floorsNum];
            for (int i = 0; i < floorsNum; i++) {
                int floorRoomsCount = Integer.parseInt(s[count++]);
                Space[] offices = new Space[floorRoomsCount];
                for (int j = 0; j < floorRoomsCount; j++) {
                    int roomNum = Integer.parseInt(s[count++]);
                    int area = Integer.parseInt(s[count++]);
                    Space space = createSpace(area, roomNum);
                    offices[j] = space;
                }
                floors[i] = createFloor(offices);
            }
            result = createBuilding(floors);
        }
        return result;
    }

    public <T extends Comparable<T>> T[] sort(T[] object){
        for (int i = 0; i < object.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < object.length; j++) {
                if(object[j].compareTo(object[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = object[i];
            object[i] = object[minIndex];
            object[minIndex] = swapBuf;
        }
        return object;
    }

    public <T> T[] sort(T[] object, Comparator<T> comparator){
        for (int i = 0; i < object.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < object.length; j++) {
                if(comparator.compare(object[j], object[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = object[i];
            object[i] = object[minIndex];
            object[minIndex] = swapBuf;
        }
        return object;
    }

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public static Space createSpace(double area){
        return buildingFactory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area){
        return buildingFactory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount){
        return buildingFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces){
        return buildingFactory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts){
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors){
        return buildingFactory.createBuilding(floors);
    }

    public static Floor synchronizedFloor (Floor floor){
        return new SynchronizedFloor(floor.getSpaceArray());
    }
}
