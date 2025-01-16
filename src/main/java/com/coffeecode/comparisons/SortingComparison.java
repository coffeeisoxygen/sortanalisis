package com.coffeecode.comparisons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.coffeecode.algorithms.BubbleSort;
import com.coffeecode.algorithms.HeapSort;
import com.coffeecode.algorithms.MergeSort;
import com.coffeecode.algorithms.QuickSort;
import com.coffeecode.results.MemoryTestResult;

public class SortingComparison {
    private final Map<String, SortingAlgorithm> algorithms;
    private final int[] dataSet;
    private final Random random;

    @FunctionalInterface
    private interface SortingAlgorithm {
        void sort(int[] array);
    }

    public SortingComparison(int size) {
        this.algorithms = new HashMap<>();
        this.dataSet = new int[size];
        this.random = new Random();
        initializeDataSet();
        initializeAlgorithms();
    }

    private void initializeDataSet() {
        for (int i = 0; i < dataSet.length; i++) {
            dataSet[i] = random.nextInt(10000); // Random numbers between 0 and 9999
        }
    }

    private void initializeAlgorithms() {
        algorithms.put("BubbleSort", BubbleSort::sort);
        algorithms.put("QuickSort", QuickSort::sort);
        algorithms.put("MergeSort", MergeSort::sort);
        algorithms.put("HeapSort", HeapSort::sort);
    }

    public Map<String, Long> measureExecutionTime() {
        Map<String, Long> results = new HashMap<>();
        
        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(dataSet, dataSet.length);
            long time = measureSort(testArray, algorithm);
            results.put(name, time);
        });

        return results;
    }

    public Map<String, Long> measureExecutionTimeWithSortedArray() {
        Map<String, Long> results = new HashMap<>();
        
        // Create sorted array
        int[] sortedArray = Arrays.copyOf(dataSet, dataSet.length);
        Arrays.sort(sortedArray);

        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(sortedArray, sortedArray.length);
            long time = measureSort(testArray, algorithm);
            results.put(name, time);
        });

        return results;
    }

    public Map<String, Long> measureExecutionTimeWithReverseArray() {
        Map<String, Long> results = new HashMap<>();
        
        // Create reverse sorted array
        int[] reverseArray = Arrays.copyOf(dataSet, dataSet.length);
        Arrays.sort(reverseArray);
        for (int i = 0; i < reverseArray.length / 2; i++) {
            int temp = reverseArray[i];
            reverseArray[i] = reverseArray[reverseArray.length - 1 - i];
            reverseArray[reverseArray.length - 1 - i] = temp;
        }

        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(reverseArray, reverseArray.length);
            long time = measureSort(testArray, algorithm);
            results.put(name, time);
        });

        return results;
    }

    public Map<String, MemoryTestResult> measureDetailedMemoryUsage() {
        Map<String, MemoryTestResult> results = new HashMap<>();
        Runtime runtime = Runtime.getRuntime();

        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(dataSet, dataSet.length);
            
            // Measure memory before sorting
            runtime.gc(); // Request garbage collection
            long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
            
            algorithm.sort(testArray);
            
            // Measure memory after sorting
            runtime.gc(); // Request garbage collection
            long afterMemory = runtime.totalMemory() - runtime.freeMemory();
            
            results.put(name, new MemoryTestResult(afterMemory - beforeMemory));
        });

        return results;
    }

    private long measureSort(int[] array, SortingAlgorithm algorithm) {
        long startTime = System.nanoTime();
        algorithm.sort(array);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
