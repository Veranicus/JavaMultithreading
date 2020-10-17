package com.patrikpolacek.producerConsumerProblem;


import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();

    private static final int UPPER_LIMIT = 5;

    private static final int LOWER_LIMIT = 0;

    private int count = 0;

    private void increaseCount() {
        synchronized (this) {
            count++;
        }
    }

    private void decreaseCount() {
        synchronized (this) {
            count--;
        }
    }

    public void producer() throws InterruptedException {

        while (count < UPPER_LIMIT) {
            list.add(count);
            increaseCount();
            System.out.println(list);
        }
    }


    public void consumer() throws InterruptedException {
        if (count == UPPER_LIMIT) {
            for (int i = 4; i > LOWER_LIMIT; i--) {
                list.remove(i);
                decreaseCount();
                System.out.println(list);
            }
        }
    }
}


public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
