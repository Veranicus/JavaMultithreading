package com.patrikpolacek.studentlibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {

        Student[] students = null;
        Book[] books = null;
        ExecutorService executorService = Executors.newFixedThreadPool(Constant.NUMBER_OF_STUDENTS);

        books = new Book[Constant.NUMBER_OF_BOOKS];
        students = new Student[Constant.NUMBER_OF_STUDENTS];

        for (int i = 0; i < Constant.NUMBER_OF_BOOKS; i++){
            books[i] = new Book(i);

        }

        for (int i = 0; i < Constant.NUMBER_OF_STUDENTS; i++){
            students[i] = new Student(i, books);
            executorService.execute(students[i]);
        }

        executorService.shutdown();
    }
}
