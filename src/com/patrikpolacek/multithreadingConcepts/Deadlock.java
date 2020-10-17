package com.patrikpolacek.multithreadingConcepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);


    public static void main(String[] args) {

        Deadlock deadlock = new Deadlock();

//        after java 8 it is possible to use lambda expressions
//        to create threads
        new Thread(deadlock::worker1, "worker1").start();
        new Thread(deadlock::worker2, "worker2").start();

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
        lock1.lock();
        System.out.println("Worker1 acquires the lock1");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("Worker1 acquired the lock2...");
        lock1.unlock();
        System.out.println("Worker1 unlocks the lock1...");
        lock2.unlock();
        System.out.println("Worker1 unlocks the lock2...");
    }

    public void worker2() {
        lock1.lock();
        System.out.println("Worker2 acquires the lock1");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("Worker2 acquired the lock2...");
        lock1.unlock();
        System.out.println("Worker2 unlocks the lock1...");
        lock2.unlock();
        System.out.println("Worker2 unlocks the lock2...");

    }
}
