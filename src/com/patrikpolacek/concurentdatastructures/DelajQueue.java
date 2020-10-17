package com.patrikpolacek.concurentdatastructures;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {

    private long duration;
    private String message;


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
//        How much time we have to wait before we can take
//        item from the queue
        return unit.convert(duration - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.getDuration() < ((DelayedWorker) o).getDuration()){
//            -1 means that this workers duration is  smaller than the other delay worker
            return -1;
        }else if (this.getDuration() > ((DelayedWorker)o).getDuration()){
            return 1;
        } else return 0;
//        Items are sorted according to delayed parameter
//        Comparator to tell java how to sort them
    }

    @Override
    public String toString() {
        return "DelayedWorker{" +
                "duration=" + duration +
                ", message='" + message + '\'' +
                '}';
    }
}

public class DelajQueue {

    public static void main(String[] args) throws InterruptedException {

//        It is used for server applications

        BlockingQueue<DelayedWorker> blockingQueue = new DelayQueue<>();

        try {
            blockingQueue.put(new DelayedWorker(1000,"This is the first message..."));
            blockingQueue.put(new DelayedWorker(7000,"This is the second message..."));
            blockingQueue.put(new DelayedWorker(6000,"This is the third message..."));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!blockingQueue.isEmpty()){
            System.out.println(blockingQueue.take());
        }

    }
}
