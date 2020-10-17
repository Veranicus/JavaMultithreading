package com.patrikpolacek.concurentdatastructures;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class WorkerOne implements Runnable {

    private ConcurrentHashMap<String, Integer> concurrentHashMap;

    public WorkerOne(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        this.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run() {
        try {
            concurrentHashMap.put("NumberOne", 45);
            Thread.sleep(2000);
            concurrentHashMap.put("NumberTwo", 68);
            Thread.sleep(100);
            concurrentHashMap.put("NumberThree", 23);
            Thread.sleep(800);
            concurrentHashMap.put("NumberFour", 11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerTwo implements Runnable {

    private ConcurrentHashMap<String, Integer> concurrentHashMap;

    public WorkerTwo(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        this.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(concurrentHashMap.get("NumberOne"));
            System.out.println(concurrentHashMap.get("NumberTwo"));
            Thread.sleep(1100);
            System.out.println(concurrentHashMap.get("NumberThree"));
            Thread.sleep(580);
            System.out.println(concurrentHashMap.get("NumberFour"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurenetHshMap {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        new Thread(new WorkerOne(concurrentHashMap)).start();
        new Thread(new WorkerTwo(concurrentHashMap)).start();

        List<String> list = new ArrayList<>();

//        Slower for synchronization
        List<String> list2 = Collections.synchronizedList(list);



    }
}
