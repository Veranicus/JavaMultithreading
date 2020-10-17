package com.patrikpolacek;

class NormalWorker implements Runnable {

    @Override
    public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello world from " + this.getClass().getSimpleName() + " thread.");
        }
    }


//Daemon threads are destroy by jvm after every other thread finishes execution
class DaemonWorker implements Runnable {

    @Override
    public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello world from " + this.getClass().getSimpleName() + " thread.");
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here

        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());
        System.out.println(t1.isDaemon());

        t1.setDaemon(true);


        t1.start();

        t2.start();

        System.out.println(t1.isDaemon());


        String n = "fdsdfds";

        n = "fdsfsad";

        System.out.println(n);

        System.out.println(Thread.currentThread().getName());
    }
}
