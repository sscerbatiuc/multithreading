package com.isd.threads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapMultipleThreads {
    public static void main(String[] args) {
        final Map<String, Integer> sharedMap = new HashMap<>();

        // Thread 1: Add elements to the HashMap
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sharedMap.put("Key: " + i, i);
                try {
                    Thread.sleep(100); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2: Iterate over the HashMap
        Thread thread2 = new Thread(() -> {
            Iterator<Map.Entry<String, Integer>> iterator = sharedMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                System.out.println(entry.getKey() + ": " + entry.getValue());

                // Simulate some processing time
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

