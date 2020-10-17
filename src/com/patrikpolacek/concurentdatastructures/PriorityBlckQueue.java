package com.patrikpolacek.concurentdatastructures;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWrker implements Runnable {


    private BlockingQueue<Person> blockingQueue;


    public FirstWrker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(new Person(10,"John"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(54,"Franny"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(2,"Miky"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(34,"Larry"));
            blockingQueue.put(new Person(21,"Diana"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWrker implements Runnable {


    private BlockingQueue<Person> blockingQueue;


    public SecondWrker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(3000);
            System.out.println(blockingQueue.take());
            Thread.sleep(400);
            System.out.println(blockingQueue.take());
            Thread.sleep(520);
            System.out.println(blockingQueue.take());
            Thread.sleep(610);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person>{

    private int age;

    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        if (this.getAge() > o.getAge()){
            return 1;
        }else if (this.getAge() < o.getAge()){
            return -1;
        }else return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class PriorityBlckQueue {

    public static void main(String[] args) {
        BlockingQueue<Person> blockingQueue = new PriorityBlockingQueue<>();

        new Thread(new FirstWrker(blockingQueue)).start();
        new Thread(new SecondWrker(blockingQueue)).start();

    }
}
