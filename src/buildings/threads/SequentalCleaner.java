package buildings.threads;

import buildings.Floor;
import buildings.Space;
import buildings.patterns.Semaphore;


public class SequentalCleaner implements Runnable {
    private Semaphore semaphore;
    private Floor floor;

    public SequentalCleaner(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    public void run() {
        for (int i = 0; i < floor.getSpaceAmount(); i++) {
            semaphore.enter();
            System.out.println(String.format("Cleaning space number %d with total area %f square meters.",
                    i, ((Space) floor.getSpace(i)).getArea()));
            semaphore.leave();
        }
        System.out.println("Cleaner has stopped working");
    }
}
