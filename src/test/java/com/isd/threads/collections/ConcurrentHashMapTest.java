package com.isd.threads.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentHashMapTest {

    @Test
    void testConcurrentHashMap() {
        try {
// Using HashMap without synchronization
            Map<String, Integer> hashMap = new HashMap<>();

            // Creating two threads to concurrently update the HashMap
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    hashMap.put("Key" + i, i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 1000; i < 2000; i++) {
                    hashMap.put("Key" + i, i);
                }
            });

            thread1.start();
            thread2.start();

            // Wait for both threads to finish
            thread1.join();
            thread2.join();

            // Uncommenting the next line may lead to ConcurrentModificationException
            System.out.println("HashMap size: " + hashMap.size());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
