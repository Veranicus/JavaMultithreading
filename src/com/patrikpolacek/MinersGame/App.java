package com.patrikpolacek.MinersGame;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static int counter = 0;
    public Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        counter++;
        System.out.println(Thread.currentThread());
        lock.unlock();
    }

    public static void main(String[] args) {

        App app = new App();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    app.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    app.increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }


}
