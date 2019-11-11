package buildings.patterns;

import buildings.Floor;

public class Semaphore {
    private int max;
    private int cur;
    private Floor lock;


    public Semaphore(int curMax, Floor floor) {
        this.max = curMax;
        lock = floor;
    }

    public void enter() {
        synchronized (lock) {
            cur++;
            if (cur > max) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }

    }

    public void leave() {
        synchronized (lock) {
            cur--;
            lock.notify();
        }

    }
}
