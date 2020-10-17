package com.patrikpolacek.multithreadingConcepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);


    public static void main(String[] args) {

        Livelock livelock = new Livelock();


//        after java 8 it is possible to use lambda expressions
//        to create threads
        new Thread(livelock::worker1, "worker1").start();
        new Thread(livelock::worker2, "worker2").start();

//        alternative way
//        new Thread(new CallableExample() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    //Deadlock state caused by cyclic dependency
    public void worker1() {

        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker 1 acquires lock 1");
            System.out.println("Worker 1 tries to acquire lock 2");

            if (lock2.tryLock()) {
                System.out.println("Worker 1 acquires the lock 2");
                lock2.unlock();
            } else {
                System.out.println("Worker1 cannot acquire lock2...");
                continue;
            }
            break;
        }
    }

    public void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker 2 acquires lock 2");
            System.out.println("Worker 2 tries to acquire lock 1");

            if (lock1.tryLock()) {
                System.out.println("Worker 2 acquires the lock 1");
                lock1.unlock();
            } else {
                System.out.println("Worker2 cannot acquire lock1...");
                continue;
            }
            break;
        }

    }
}
