package com.patrikpolacek.producerConsumerProblem;


import java.util.ArrayList;
import java.util.List;

class Processor2 {

    private List<Integer> list = new ArrayList<>();

    private static final int UPPER_LIMIT = 5;

    private static final int LOWER_LIMIT = 0;

    private final Object lock = new Object();

    private int value = 0;




    public void producer() throws InterruptedException {
        synchronized (lock){
            while (true){
                if (list.size() == UPPER_LIMIT){
                    System.out.println("Waiting for removing items");
                    lock.wait();
                }else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
//                    we can call the notify because the other thread will
//                    be notify only when it is in a waiting state
                    lock.notify();
                }
            }
        }

    }


    public void consumer() throws InterruptedException {
        synchronized (lock){
            while (true){
                if (list.size() == LOWER_LIMIT){
                    System.out.println("Waiting for removing items");
                    lock.wait();
                }else {
                    System.out.println("Removing: " + list.remove(list.size() - 1));
                    value--;
//                    we can call the notify because the other thread will
//                    be notify only when it is in a waiting state
                    lock.notify();
                }
            }
        }

    }
}


public class App2 {

    public static void main(String[] args) {

        Processor2 processor2 = new Processor2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor2.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor2.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
