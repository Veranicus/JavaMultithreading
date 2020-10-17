package com.patrikpolacek.threadsmanipulation;

class Runner1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println(this.getClass().getSimpleName() + " " + i);
        }
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println(this.getClass().getSimpleName() + " " + i);
        }
    }
}

class Runner4 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getClass().getSimpleName() + " " + i);
        }
    }
}

public class App {

    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        Thread t1 = new Thread(runner1);
        Thread t2 = new Thread(runner2);

//        t1.start();
//        t2.start();

        Thread t3 = new Thread(new Runner1());
        Thread t4 = new Thread(new Runner2());

        Thread t6 = new Runner4();


        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    System.out.println("Runner 5: " + i);
                }
            }
        });
// It is not parallel execution !
// this is concurent execution, jvm & cpu will use time slicing alghoritm
//
        t3.start();
        t4.start();
        t5.start();
        t6.start();

//        we can wait for the thread to finish: join()

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finished with the threads");
    }
}
