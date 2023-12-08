package com.isd.threads.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingRunnable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task1 = () -> {
            System.out.println("Task 1 is running in thread: " + Thread.currentThread().getName());
        };

        Runnable task2 = () -> {
            System.out.println("Task 2 is running in thread: " + Thread.currentThread().getName());
        };

        Runnable task3 = () -> {
            System.out.println("Task 3 is running in thread: " + Thread.currentThread().getName());
        };

        // Cannot do this because invokeAll() expects a List<Callable>
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        // Shutdown the ExecutorService (not necessary in a short-lived application)
        executorService.shutdown();
    }
}
