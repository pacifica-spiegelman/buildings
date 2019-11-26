import buildings.Floor;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.patterns.Semaphore;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;




public class Main {

    public static void main(String[] args) {
        /* Building
        int[] flats = {2, 3, 4, 4, 6};
        Dwelling dwelling = new Dwelling(5, flats);
        dwelling.addSpace(0, new Flat(80, 4));
        dwelling.addSpace(1, new Flat(20, 1));
        dwelling.addSpace(2, new Flat(35, 2));
        dwelling.addSpace(3, new Flat(46, 3));
        dwelling.addSpace(4, new Flat(26, 1));
        dwelling.addSpace(5, new Flat(78, 4));
        dwelling.addSpace(6, new Flat(66, 3));
        dwelling.addSpace(7, new Flat(50, 2));
        System.out.println("Flat 6: " + "Area: " + dwelling.getSpace(6).getArea() + " Room: " + dwelling.getSpace(6).getRoom());
        dwelling.setSpace(6, new Flat(90, 5));
        System.out.println("Flat 6: " + "Area: " + dwelling.getSpace(6).getArea() + " Room: " + dwelling.getSpace(6).getRoom());
        dwelling.deleteSpace(6);
        System.out.println("Flat 6: " + "Area: " + dwelling.getSpace(6).getArea() + " Room: " + dwelling.getSpace(6).getRoom());*/

        // Offices
        /*int[] office = {2, 3, 4};
        OfficeBuilding officeBuilding = new OfficeBuilding(3, office);
        for (int i = 1; i <= officeBuilding.getFloorAmount(); i++) {
            System.out.println(officeBuilding.getFloor(i).getSpaceAmount());
        }
        officeBuilding.addSpace(0, new Office(5, 5));
        for (int i = 1; i <= officeBuilding.getFloorAmount(); i++) {
            System.out.println(officeBuilding.getFloor(i).getSpaceAmount());
        }
        for (int i = 1; i <= officeBuilding.getFloorAmount(); i++) {
                System.out.println(officeBuilding.getFloor(i).getSpaceArea());
        }
        /*int[] flats = {2, 3, 4};
        Dwelling dwelling = new Dwelling(3, flats);
        for(int i = 0; i < dwelling.getFloorAmount(); i++){
            for (int j = 0; j < dwelling.getFloor(i).getSpaceAmount(); j++) {
                System.out.println(dwelling.getFloor(i).getSpace(j).getArea());
            }
        }*/
//        DwellingFloor floor = new DwellingFloor(10);
//        Semaphore sem = new Semaphore(1, floor);
//        Thread thread1 = new Thread(new SequentalRepairer(floor, sem));
//        Thread thread2 = new Thread(new SequentalCleaner(floor, sem));
//        thread1.start();
//        thread2.start();
        new BuildingsForm();
    }
}
