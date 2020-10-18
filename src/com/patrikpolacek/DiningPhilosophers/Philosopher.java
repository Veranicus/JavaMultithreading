package com.patrikpolacek.DiningPhilosophers;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;

public class Philosopher implements Runnable {

    private int id;

    private volatile boolean full;

    private Chopstick leftChopstick;

    private Chopstick rightChopstick;

    private Random random;

    private int eatingCounter;

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }


    public int getEatingCounter() {
        return eatingCounter;
    }

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }


    private void think() throws InterruptedException {
        System.out.println(this + " is thinking");
//        Philosopher thinks for a random time
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating.");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    //After eating a lot we will terminate given thread
    @Override
    public void run() {
        while (!full) {
            try {
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
