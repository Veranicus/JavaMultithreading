package com.patrikpolacek.multithreadingConcepts;

public class AtomicInteger {
//    There are also atomic longs, booleans, arrays...
    private java.util.concurrent.atomic.AtomicInteger counter = new java.util.concurrent.atomic.AtomicInteger(0);

    public void increment() {
        for (int i = 0; i < 1000000; i++) {
            counter.incrementAndGet();
        }
    }

    public java.util.concurrent.atomic.AtomicInteger getCounter() {
        return counter;
    }

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

//        new Thread(atomicInteger::increment, "thread1").start();
//        new Thread(atomicInteger::increment, "thread2").start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.increment();
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

        System.out.println(atomicInteger.getCounter());
    }
}
