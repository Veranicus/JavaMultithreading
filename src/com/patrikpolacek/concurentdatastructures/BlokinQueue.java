package com.patrikpolacek.concurentdatastructures;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;


    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {
               int number = blockingQueue.take();
                System.out.println("Taking item from the queue " + number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;


    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        int counter = 0;
        while (true) {
            try {
                System.out.println("Putting item into the queue " + counter);
                blockingQueue.put(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlokinQueue {

    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}
