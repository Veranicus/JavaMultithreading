package com.patrikpolacek.concurentdatastructures;


class FirstThread implements Runnable{

    private int counter;

    private java.util.concurrent.Exchanger<Integer> exchanger;


    public FirstThread(java.util.concurrent.Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true){
            counter++;
            System.out.println("First thread incremented the counter " + counter);
            try {
                Thread.sleep(400);
                counter = exchanger.exchange(this.counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable{

    private int counter;

    private java.util.concurrent.Exchanger<Integer> exchanger;


    public SecondThread(java.util.concurrent.Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true){
            counter--;
            System.out.println("Second thread decremented the counter " + counter);

            try {
                Thread.sleep(400);
                counter = exchanger.exchange(this.counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Exchanger {

    public static void main(String[] args) {

        java.util.concurrent.Exchanger<Integer> exchanger = new java.util.concurrent.Exchanger<>();
        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();

    }
}
