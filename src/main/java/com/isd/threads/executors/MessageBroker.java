package com.isd.threads.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBroker {
    private final BlockingQueue<String> messageQueue;
    private final ExecutorService executorService;

    public MessageBroker() {
        this.messageQueue = new LinkedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void sendMessage(String message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void startConsumers(int numberOfConsumers) {
        for (int i = 0; i < numberOfConsumers; i++) {
            executorService.submit(() -> {
                while (true) {
                    try {
                        System.out.println("Consumer " + Thread.currentThread().getName() + " waiting for message...");
                        String message = messageQueue.take();
                        processMessage(message);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    private void processMessage(String message) {
        System.out.println("Processing message: " + message);
    }

    public void stop() throws InterruptedException {
        // Oracle recommends this approach for stopping executors
        if (!executorService.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }
}

class Demo {
    public static void main(String[] args) throws InterruptedException {
        MessageBroker messageBroker = new MessageBroker();

        // Start consumers
        messageBroker.startConsumers(5);

        // Send messages to the broker
        for (int i = 0; i < 50; i++) {
            messageBroker.sendMessage("Message " + i);
        }


        // Stop the message broker
        messageBroker.stop();
    }
}