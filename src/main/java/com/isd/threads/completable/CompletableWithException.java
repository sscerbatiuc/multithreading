package com.isd.threads.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableWithException {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 10 / 0;
            return result;
        });

        future.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return 0; // Default value to return if there's an exception
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        });


    }
}
