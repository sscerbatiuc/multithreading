package com.isd.threads.race;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Using this class will allow implementing stable counter
 * and not use synchronization blocks or synchronized methods
 */
public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }
}
