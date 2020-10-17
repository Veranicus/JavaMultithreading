package com.patrikpolacek.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task2 implements Runnable {
    private int id;

    public Task2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task2 with id " + id + " is in work - thread id:"
                + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 1);
        try {
//            emulating work
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class FixedThreadExecutor {


    public static void main(String[] args) {
//Single thread that will execute the task sequentially (one after another)
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 30; i++) {
            executorService.execute(new Task2(i));
        }

//        We prevent the executor to execute any further tasks
        executorService.shutdown();

//        terminate actual (running) tasks
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
//          Use this only if you want to terminate executor right now
//                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

//        Application will not be terminated after finishing all
//        tasks !! We have to shut down executor


    }
}
