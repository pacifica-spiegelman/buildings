package buildings.threads;

import buildings.Floor;

import java.util.concurrent.Semaphore;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore sem;
    private boolean full = false; // выполнился ли поток
    private String name = "Repairing";

    public SequentalRepairer(Floor floor, Semaphore sem){
        this.floor = floor;
        this.sem = sem;
        Thread thread = new Thread(this, name);
        thread.start();
    }



    @Override
     public void run() {
        try{
            if(!full){
                sem.acquire();
                full = true;
                for (int i = 0; i < floor.getSpaceAmount(); i++) {
                    System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
                    Thread.sleep(300);
                }
                sem.release();
            }
        }catch (InterruptedException e){
            System.out.println("Something wrong!");
        }
    }
}
