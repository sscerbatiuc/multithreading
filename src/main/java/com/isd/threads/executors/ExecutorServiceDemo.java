package com.isd.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceDemo {
    public static void main(String[] args) {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                System.out.println("Thread name: " + Thread.currentThread().getName());
            });
        }

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit(() -> {
                System.out.println("Thread name: " + Thread.currentThread().getName());
            });
        }

        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.submit(() -> {
                System.out.println("Thread name: " + Thread.currentThread().getName());
            });
        }

        // Executor for running fork/join tasks
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            workStealingPool.submit(() -> {
                System.out.println("Thread name: " + Thread.currentThread().getName());
            });
        }
    }
}
