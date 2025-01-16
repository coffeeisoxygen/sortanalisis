package com.coffeecode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coffeecode.comparisons.SortingComparison;
import com.coffeecode.config.SortingConfiguration;
import com.coffeecode.results.SortingResult;

public class SortingBenchmarkService {

    private final SortingConfiguration config;

    public SortingBenchmarkService(SortingConfiguration config) {
        this.config = config;
    }

    public List<SortingResult> runBenchmark() {
        List<SortingResult> results = new ArrayList<>();

        for (int size : config.getArraySizes()) {
            for (String algorithm : new String[]{"BubbleSort", "QuickSort", "MergeSort", "HeapSort"}) {
                SortingResult result = runAlgorithmBenchmark(algorithm, size);
                results.add(result);
            }
        }

        return results;
    }

    private SortingResult runAlgorithmBenchmark(String algorithm, int size) {
        double randomTotal = 0, sortedTotal = 0, reverseTotal = 0;
        double memoryTotal = 0;

        for (int i = 0; i < config.getNumberOfRuns(); i++) {
            SortingComparison comparison = new SortingComparison(size);

            // Measure times
            randomTotal += getAverageTime(comparison.measureExecutionTime(), algorithm);
            sortedTotal += getAverageTime(comparison.measureExecutionTimeWithSortedArray(), algorithm);
            reverseTotal += getAverageTime(comparison.measureExecutionTimeWithReverseArray(), algorithm);

            // Measure memory if enabled
            if (config.includeMemoryStats()) {
                memoryTotal += comparison.measureDetailedMemoryUsage().get(algorithm).getMemoryUsed();
            }
        }

        // Calculate averages
        int runs = config.getNumberOfRuns();
        return new SortingResult.Builder()
                .withAlgorithm(algorithm)
                .withDataSize(size)
                .withRandomArrayTime(randomTotal / runs)
                .withSortedArrayTime(sortedTotal / runs)
                .withReverseArrayTime(reverseTotal / runs)
                .withMemoryUsed(memoryTotal / runs)
                .build();
    }

    private double getAverageTime(Map<String, Long> results, String algorithm) {
        return results.get(algorithm) / 1_000_000.0; // Convert to milliseconds
    }
}
