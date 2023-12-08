package com.isd.threads.executors;

import java.util.concurrent.Executor;


/**
 * This class demonstrates how to use an Executor to run a task.
 * The task is a simple sorting algorithm.
 * The Executor is a functional interface that has a single method execute(Runnable command).
 * The executor is created using a method reference Runnable::run.
 */
public class PureExecutor {
    public static void main(String[] args) {

        Executor executor = Runnable::run;
        executor.execute(() -> {
            // simple task that sorts an array
            int[] array = {1, 5, 3, 2, 4};
            for (int i = 0; i < array.length; i++) {
                for (int j = i; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        int tmp = array[i];
                        array[i] = array[j];
                        array[j] = tmp;
                    }
                }
            }
            System.out.println("Thread name: " + Thread.currentThread().getName());
            System.out.println("Sorted array: " + java.util.Arrays.toString(array));
        });

        System.out.println("Thread name: " + Thread.currentThread().getName());
    }
}
