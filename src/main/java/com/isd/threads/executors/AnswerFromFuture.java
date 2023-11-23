package com.isd.threads.executors;

import java.util.concurrent.*;

public class AnswerFromFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> futureResult = executorService.submit(() -> {
            // Simulate a time-consuming task
            Thread.sleep(2000);
            return "Hello from the Future!";
        });


        try {
            // Block and get the result when it's ready
            String result = futureResult.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}