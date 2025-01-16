package com.coffeecode.comparisons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.coffeecode.algorithms.BubbleSort;
import com.coffeecode.algorithms.HeapSort;
import com.coffeecode.algorithms.MergeSort;
import com.coffeecode.algorithms.QuickSort;

public class SortingComparison {

    private final Map<String, SortingFunction> algorithms;
    private final int[] dataSet;

    public SortingComparison(int size) {
        this.algorithms = new HashMap<>();
        this.dataSet = generateRandomArray(size);
        initializeAlgorithms();
    }

    private void initializeAlgorithms() {
        algorithms.put("Bubble Sort", BubbleSort::sort);
        algorithms.put("Heap Sort", HeapSort::sort);
        algorithms.put("Merge Sort", MergeSort::sort);
        algorithms.put("Quick Sort", QuickSort::sort);
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

    public Map<String, MemoryTestResult> measureDetailedMemoryUsage() {
        Map<String, MemoryTestResult> results = new HashMap<>();

        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(dataSet, dataSet.length);
            Runtime runtime = Runtime.getRuntime();
            long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
            long peakMemory = beforeMemory;

            algorithm.sort(testArray);

            long afterMemory = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = afterMemory - beforeMemory;
            peakMemory = Math.max(peakMemory, afterMemory);

            results.put(name, new MemoryTestResult(name, memoryUsed, peakMemory));
        });

        return results;
    }

    public Map<String, int[]> visualDemo(int sampleSize) {
        Map<String, int[]> results = new HashMap<>();
        int[] sample = Arrays.copyOf(dataSet, Math.min(sampleSize, dataSet.length));

        algorithms.forEach((name, algorithm) -> {
            int[] testArray = Arrays.copyOf(sample, sample.length);
            algorithm.sort(testArray);
            results.put(name, testArray);
        });

        return results;
    }

    private static long measureSort(int[] array, SortingFunction sorter) {
        long startTime = System.nanoTime();
        sorter.sort(array);
        return System.nanoTime() - startTime;
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    @FunctionalInterface
    public interface SortingFunction {

        void sort(int[] array);
    }
}
