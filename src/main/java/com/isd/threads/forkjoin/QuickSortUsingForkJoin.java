package com.isd.threads.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortUsingForkJoin extends RecursiveAction {
    private final int[] array;
    private final int low;
    private final int high;

    public QuickSortUsingForkJoin(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            int pivotIndex = getPivot(array, low, high);

            QuickSortUsingForkJoin leftTask = new QuickSortUsingForkJoin(array, low, pivotIndex - 1);
            QuickSortUsingForkJoin rightTask = new QuickSortUsingForkJoin(array, pivotIndex + 1, high);

            invokeAll(leftTask, rightTask);
        }
    }

    /**
     * This method partitions given array and returns the pivot index
     * @param array The array to be sorted
     * @param low The lower index of the array
     * @param high The higher index of the array
     * @return The pivot index
     */
    private int getPivot(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(10000000);

        // measure the execution time
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        QuickSortUsingForkJoin quickSortTask = new QuickSortUsingForkJoin(array, 0, array.length - 1);

        long startTime = System.currentTimeMillis();
        forkJoinPool.invoke(quickSortTask);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  // In milliseconds

        // Verify the sorted array
//        System.out.println(Arrays.toString(array));
        System.out.println("Duration: " + duration + " milliseconds");
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        return array;
    }
}