package com.isd.threads.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruption {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread waitingThread = new Thread(() -> {
            try {
                lock.lockInterruptibly(); // Acquires the lock or waits until interrupted
                System.out.println("Thread is working after acquiring the lock");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupted status
                System.out.println("Thread was interrupted while waiting for the lock");
            } finally {

            }
        });

        lock.lock();
        waitingThread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Interrupt the waiting thread
        waitingThread.interrupt();
    }
}
