package com.isd.threads.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This class demonstrates how to use RecursiveAction in ForkJoinPool
 * to double each element in an array.
 * The array is divided into smaller arrays recursively until the size of the array is less than or equal to 25.
 */
public class ForkJoinDoubleAction {
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        return array;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] array = generateRandomArray(100);
        DoubleNumber doubleNumberTask = new DoubleNumber(array, 0, array.length);

        // Invokes compute method
        forkJoinPool.invoke(doubleNumberTask);
        System.out.println(DoubleNumber.result);
    }
}

class DoubleNumber extends RecursiveAction {

    final int maxSize = 25;
    int[] array;
    int startIndex, endIndex;
    static int result;

    DoubleNumber(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected void compute() {
        if (endIndex - startIndex <= maxSize) {
            for (int i = startIndex; i < endIndex; i++) {
                result += array[i] * 2;
            }
        } else {
            int mid = (startIndex + endIndex) / 2;
            DoubleNumber leftArray = new DoubleNumber(array, startIndex, mid);
            DoubleNumber rightArray = new DoubleNumber(array, mid, endIndex);

            // Invokes the compute method recursively
            leftArray.fork();
            rightArray.fork();

            // Joins results from recursive invocations
            leftArray.join();
            rightArray.join();
        }
    }
}
