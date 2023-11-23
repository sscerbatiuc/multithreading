package com.isd.threads.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHelloWorld {
    public static void main(String[] args) {
        // Start the initial CompletableFuture
        CompletableFuture<String> initialFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> chainedFuture = initialFuture.thenComposeAsync(result -> CompletableFuture.supplyAsync(() -> result + " World"));

        CompletableFuture<String> finalFuture = chainedFuture.thenApply(result -> result + "!");

        try {
            // Get the result of the final CompletableFuture
            String finalResult = finalFuture.get();
            System.out.println(finalResult); // Output: Hello World!
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
