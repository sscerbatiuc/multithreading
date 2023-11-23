package com.isd.threads.data.memory;

import java.util.Arrays;
import java.util.Random;

public class ArraySum {

    static int[] arraySizes = {
            100000,    // too small
            1000000,   // still too small
            10000000,  // okay
            100000000, // now there should be a difference
            1000000000 // now we're talking
    };

    public static void main(String[] args) {
        for (int arraySize : arraySizes) {
            System.out.println("----------------------------------------------------------");
            System.out.println("Array size: " + arraySize);
            int[] data = RandomArrayGenerator.getArray(arraySize);

            long startTime = System.currentTimeMillis();
            System.out.println("Sequential sum: " + Arrays.stream(data).sum());
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime); // In milliseconds
            System.out.println("Sequential sum duration: " + duration + " milliseconds");

            startTime = System.currentTimeMillis();
            System.out.println("Parallel sum: " + Arrays.stream(data).parallel().sum());
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime); // In milliseconds
            System.out.println("Parallel sum duration: " + duration + " milliseconds");
        }

    }

}


class RandomArrayGenerator {
    public static int[] getArray(int arraySize) {
        int[] largeArray = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            largeArray[i] = random.nextInt(); // Populate with random values
        }

        return largeArray;
    }
}