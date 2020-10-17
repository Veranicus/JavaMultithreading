package com.patrikpolacek.multithreadingConcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semaphores {

//    Semaphore has two important methods
//    acquire - if the permit is available then it takes it
//    release - adds a permit

//    Semaphore just keeps truck of the number available
//    Semaphore(int permits, boolean fair), fair means that longest
//    waiting thread is going to acquire the lock


    enum Downloader{
        INSTANCE;
        private Semaphore semaphore = new Semaphore(5,true);
        public void downloadData(){
            try {
                semaphore.acquire();
                download();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }

        private void download() {
            System.out.println("downloading data from the web..");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        ExecutorService executorService =  Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

    }
}
