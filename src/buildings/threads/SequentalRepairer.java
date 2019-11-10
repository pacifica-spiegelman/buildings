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
        int i = 2;
        for (var space :
                floor) {
            semaphore.enter(floor);
            System.out.println(String.format("Repairer space number %d with total area %f square meters.",
                    i, ((Space) space).getArea()));
            i++;
            semaphore.leave(floor);
        }
        System.out.println("Cleaner has stopped working");
    }
}
