package buildings.threads;

import buildings.Floor;
import buildings.Space;
import buildings.patterns.Semaphore;


public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    public void run() {
        for (int i = 0; i<floor.getSpaceAmount(); i++) {
            semaphore.enter();
            System.out.println(String.format("Repairer space number %d with total area %f square meters.",
                    i, ((Space) floor.getSpace(i)).getArea()));
            semaphore.leave();
        }
        System.out.println("Repairer has stopped working");
    }
}
