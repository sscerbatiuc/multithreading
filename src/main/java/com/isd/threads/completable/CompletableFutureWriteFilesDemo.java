package com.isd.threads.completable;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureWriteFilesDemo {

    public static void main(String[] args) {
        // Specify the path to the text file in the project's root directory
        String fileName = "output.txt";
        String filePath = Paths.get("").toAbsolutePath() + "/" + fileName;

        // Data from three separate sources
        String source1Data = "Data from Source 1\n";
        String source2Data = "Data from Source 2\n";
        String source3Data = "Data from Source 3\n";

        // Create CompletableFuture tasks for writing data from each source
        CompletableFuture<Void> writeTask1 = CompletableFuture.runAsync(() -> writeToTextFile(filePath, source1Data));
        CompletableFuture<Void> writeTask2 = CompletableFuture.runAsync(() -> writeToTextFile(filePath, source2Data));
        CompletableFuture<Void> writeTask3 = CompletableFuture.runAsync(() -> writeToTextFile(filePath, source3Data));

        // Combine results from all CompletableFuture tasks
        CompletableFuture<Void> allOf = CompletableFuture.allOf(writeTask1, writeTask2, writeTask3);

        try {
            allOf.get(); // This blocks until all CompletableFuture tasks are complete
            System.out.println("Data has been written to the file successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToTextFile(String filePath, String data) {
        try {
            Files.write(Paths.get(filePath), data.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println("Data has been written to the file successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}