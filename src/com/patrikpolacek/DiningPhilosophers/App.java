package com.patrikpolacek.DiningPhilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

//    The aim of the simulation is to avoid thread starvation

    public static void main(String[] args) {

        ExecutorService executorService = null;

        Philosopher[] philosophers = null;

        Chopstick[] chopsticks = null;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
                executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

            }
            for (int j = 0; j < Constants.NUMBER_OF_PHILOSOPHERS; j++) {
                philosophers[j] = new Philosopher(j, chopsticks[j],
                        chopsticks[(j + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[j]);
            }
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
            for (Philosopher p : philosophers) {
                p.setFull(true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                try {
                    Thread.sleep(1000);
                    for (Philosopher p : philosophers) {
                        System.out.println(p + " eat # " + p.getEatingCounter() + " times.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
