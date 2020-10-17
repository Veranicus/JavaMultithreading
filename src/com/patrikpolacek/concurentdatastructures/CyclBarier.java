package com.patrikpolacek.concurentdatastructures;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Cycling barier is similar to countdown latch
//We can reset cycling barier unlike the countdown latch
//Also there is runnable that will run once count hits zero
public class CyclBarier {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("All the tasks are finished");
                        System.out.println("We are for example able to use the trained" +
                                "neural network");
                    }
                });
        for (int i = 0; i < 5; i++) {
            executorService.execute(new WrokerTask(i + 1, cyclicBarrier));
        }
        executorService.shutdown();
    }
}

class WrokerTask implements Runnable {

    private int id;

    private Random random = new Random();

    private CyclicBarrier cyclicBarrier;

    public WrokerTask(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id " + id + " starts the task");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with ID " + id + " finished.");
        try {
            cyclicBarrier.await();
            System.out.println("After await");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "WrokerTask{" +
                "id=" + id +
                ", random=" + random +
                ", cyclicBarrier=" + cyclicBarrier +
                '}';
    }
}