package com.isd.threads.race;

public class CounterDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                counter.increment();
                builder.append("T1 counter get value: ").append(counter.getValue()).append("\n");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(builder);
        });
        Thread t2 = new Thread(() -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                counter.increment();
                builder.append("T2 counter get value: ").append(counter.getValue()).append("\n");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(builder);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Final counter value: " + counter.getValue());

    }
}
