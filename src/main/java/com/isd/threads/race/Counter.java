package com.isd.threads.race;

public class Counter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
