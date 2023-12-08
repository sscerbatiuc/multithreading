package com.isd.threads.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingCallable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callableTask = () -> {
            Thread.sleep(2000);
            return "42";
        };

        try {
            Future<String> futureResult = executorService.submit(callableTask);

            String result = futureResult.get();

            System.out.println(result);
        } catch (Exception e) {
          // Oh noo!
        } finally {
            executorService.shutdown();
        }
    }
}