package com.isd.threads.forkjoin;

import java.util.Arrays;

public class QuickSortSingleThread {

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(10000000); // Adjust the size as needed
        System.out.println("Original array: " + Arrays.toString(array));

        // measure the execution time
        long startTime = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  // In milliseconds

        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Duration: " + duration + " milliseconds");
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}