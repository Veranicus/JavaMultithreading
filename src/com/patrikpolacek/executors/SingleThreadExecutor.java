package com.patrikpolacek.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task2 with id " + id + " is in work - thread id:"
                + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SingleThreadExecutor {


    public static void main(String[] args) {
//Single thread that will execute the task sequentially (one after another)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            executorService.execute(new Task2(i));
        }

//        Application will not be terminated after finishing all
//        tasks !! We have to shut down executor



    }
}
