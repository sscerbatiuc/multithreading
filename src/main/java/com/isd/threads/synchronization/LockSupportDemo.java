package com.isd.threads.synchronization;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        // Create a separate thread
        Thread otherThread = new Thread(() -> {
            // Simulate some work
            try {
                System.out.println("Other thread is doing some work...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Un-park the main thread after some work
            LockSupport.unpark(mainThread);
        });

        // Start the other thread
        otherThread.start();

        System.out.println("Main thread is waiting...");

        // Park the main thread until it is unparked by the other thread
        LockSupport.park();

        System.out.println("Main thread is unparked and continues execution.");
    }
}


class CustomLock {
    private volatile boolean isLocked = false;
    private Thread lockedBy = null;

    public void lock() {
        while (!tryLock()) {
            // If the lock is not available, park the current thread
            LockSupport.park();
        }
    }

    public boolean tryLock() {
        if (!isLocked) {
            // Atomically check and set the lock status
            isLocked = true;
            lockedBy = Thread.currentThread();
            return true;
        }
        return false;
    }

    public void unlock() {
        if (Thread.currentThread() != lockedBy) {
            throw new IllegalMonitorStateException("Calling thread does not hold the lock");
        }

        // Reset the lock status and unpark any waiting threads
        isLocked = false;
        lockedBy = null;
        LockSupport.unpark(lockedBy);
    }
}