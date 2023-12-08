package com.isd.threads.completable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturesWithException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       try{
           ExecutorService executorService = Executors.newSingleThreadExecutor();

           Future<Integer> result = executorService.submit(() -> {
               return 10/0;
           });

           Integer i = result.get();
           System.out.println("Result: " + i);
           executorService.shutdownNow();
       } catch (Exception e){
           System.out.println("Exception: " + e.getMessage());
       }
    }
}
