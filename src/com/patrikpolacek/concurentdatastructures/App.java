package com.patrikpolacek.concurentdatastructures;

//Countdown latches are used to synchronize one or more task
//After 10000 things happen we do something specific

//Countdown latches cannot be increased

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {

    private int id;

    private CountDownLatch countDownLatch;

    private Random random;


    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println("Thread with id " + this.id + " starts working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i + 1, countDownLatch));
        }
            try {
//                We will wait until the countdown method is called 5 times
//                Until then the application thread is going to wait
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        System.out.println("All the prerequisites are done...");
        executorService.shutdown();


    }
}
