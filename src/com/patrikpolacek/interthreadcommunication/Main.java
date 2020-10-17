package com.patrikpolacek.interthreadcommunication;

public class Main {

    public static int counter1 = 0;
    public static int counter2 = 0;

//Our custom object to represent lock

//    Executing at the same time is not paralel procesing !!!
//    cpu is going to use time slicing alghoritm to deal with both of these
//    threads
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

//    we have to make sure this method is executed only by a single thread
//    at a given time
    public static void increment1(){
//        Class level locking !
//        rule of thumb that we synchronized blocks that are
//        100% necesarry. Just given operation that needs to be thread save
        synchronized (lock1) {
            counter1++;
        }
    }
//Intrinsic lock(monitor) of given class is needed
//    Time slicing alghoritm
//    If we use synchronized with static we get it locked on the clas level
    public static synchronized void increment2(){
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                   increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    increment2();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("The counter 1 is " + counter1);
        System.out.println("The counter 2 is " + counter2);
    }

    public static void main(String[] args) {
        process();
    }
}
