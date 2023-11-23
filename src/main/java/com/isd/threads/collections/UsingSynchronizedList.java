package com.isd.threads.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

public class UsingSynchronizedList {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {

                    if (!list.contains("foo")) {
                        try {
                            Thread.sleep(100); // or another heavy operation
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        list.add("foo");
                        System.out.println("Added foo");
                    }

            });

        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class ListThatIsSynchronized{

    private static final ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("Attempt thread: " + Thread.currentThread().getName());
                lock.lock();
                if (!list.contains("foo")) {
                    try {
                        Thread.sleep(1);
                        list.add("foo");
                        System.out.println("Added foo");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.unlock();
            }, "Thread " + i);

        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
