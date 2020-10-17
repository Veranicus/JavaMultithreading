package com.patrikpolacek.multithreadingConcepts;

class Worker implements Runnable {

//    It will be stored in the main memory with use of volatile
//    works as a flag variable
//    a way of terminating thread
    private volatile boolean terminated;


    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public void run() {
        int i = 0;
        while (!isTerminated()){
            System.out.println("Working class is running..." + i);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}

public class Volatile {


    public static void main(String[] args) {

        Worker worker = new Worker();

        Thread t1 = new Thread(worker);

        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);
        System.out.println("Alghoritm is terminated...");
    }

}
