package com.patrikpolacek.studentlibrary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

    private int id;

    private Lock lock;

    public Book(int id){
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
     if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
         System.out.println(student + " starts reading " + this);
         Thread.sleep(2000);
         lock.unlock();
         System.out.println(student + " finishes reading " + this);
     }else {
         System.out.println("Book" + this  + " already read by another student");
     }
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id + '}';
    }
}
