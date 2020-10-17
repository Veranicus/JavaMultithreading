package com.patrikpolacek.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method..");
//        same as wait
        condition.await();
        System.out.println("Producer again...");
//        Unlock must be called same number of times as lock
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer method..");
//        same as notify
        condition.signal();
        lock.unlock();
    }
}

public class ProducerConsumerrLocks {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    }
}
