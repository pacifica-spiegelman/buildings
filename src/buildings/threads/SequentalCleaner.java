package buildings.threads;

import buildings.Floor;
import buildings.Space;
import buildings.patterns.Semaphore;


public class SequentalCleaner implements Runnable {
    private Floor floor;
    private Semaphore semaphore;

    public SequentalCleaner( Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    public void run() {
        int i = 1;
        for (var space :
                floor) {
            semaphore.enter(floor);
            System.out.println(String.format("Cleaning space number %d with total area %f square meters.",
                    i, ((Space) space).getArea()));
            i++;
            semaphore.leave(floor);
        }
        System.out.println("Cleaner has stopped working");
    }
}
