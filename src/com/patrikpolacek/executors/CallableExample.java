package com.patrikpolacek.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements java.util.concurrent.Callable<String>{

    private int id;

    public Processor(int id) {
        this.id = id;
    }


    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id :" + id;
    }
}

public class CallableExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futuresList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Future<String> future = executorService.submit(new Processor(i + 1));
            futuresList.add(future);
        }

        for (Future<String> futureValue: futuresList){
            try {
                System.out.println(futureValue.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }
}
