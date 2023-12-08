package com.isd.threads.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OutOfMemorySimulation {

    public static void main(String[] args) {
        // Create a single-threaded executor
        ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(1);

        try {
            singleThreadExecutor.execute(() -> simulateOutOfMemory());
        } finally {
            singleThreadExecutor.shutdown();
        }
    }

    private static void simulateOutOfMemory() {
        try {
            byte[] bigArray = new byte[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            System.out.println("Out of Memory Error occurred!");
            e.printStackTrace();
        }
    }
}
