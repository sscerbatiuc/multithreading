package com.isd.threads.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHelloWorld {
    public static void main(String[] args) {
        CompletableFuture<String> initialFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> chainedFuture = initialFuture.thenComposeAsync(result -> CompletableFuture.supplyAsync(() -> result + " World"));

        chainedFuture.thenApply(result -> result + "!")
                .whenComplete((s, throwable) -> System.out.println("Completed"));

        System.out.println("Main thread");
    }
}
