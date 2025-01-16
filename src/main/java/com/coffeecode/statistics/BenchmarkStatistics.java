package com.coffeecode.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coffeecode.results.SortingResult;

public class BenchmarkStatistics {

    private final List<SortingResult> results;
    private final int[] arraySizes;

    public BenchmarkStatistics(List<SortingResult> results, int[] arraySizes) {
        this.results = results;
        this.arraySizes = arraySizes;
    }

    public Map<String, double[]> getRandomArrayStats() {
        Map<String, double[]> stats = new HashMap<>();
        for (String algo : new String[]{"BubbleSort", "QuickSort", "MergeSort", "HeapSort"}) {
            double[] times = new double[arraySizes.length];
            for (int i = 0; i < arraySizes.length; i++) {
                final int index = i;
                times[index] = results.stream()
                        .filter(r -> r.getAlgorithmName().equals(algo) && r.getDataSize() == arraySizes[index])
                        .mapToDouble(SortingResult::getRandomArrayTime)
                        .average()
                        .orElse(0.0);
            }
            stats.put(algo, times);
        }
        return stats;
    }

    public Map<String, double[]> getSortedArrayStats() {
        Map<String, double[]> stats = new HashMap<>();
        for (String algo : new String[]{"BubbleSort", "QuickSort", "MergeSort", "HeapSort"}) {
            double[] times = new double[arraySizes.length];
            for (int i = 0; i < arraySizes.length; i++) {
                final int index = i;
                times[index] = results.stream()
                        .filter(r -> r.getAlgorithmName().equals(algo) && r.getDataSize() == arraySizes[index])
                        .mapToDouble(SortingResult::getSortedArrayTime)
                        .average()
                        .orElse(0.0);
            }
            stats.put(algo, times);
        }
        return stats;
    }

    public Map<String, double[]> getReverseArrayStats() {
        Map<String, double[]> stats = new HashMap<>();
        for (String algo : new String[]{"BubbleSort", "QuickSort", "MergeSort", "HeapSort"}) {
            double[] times = new double[arraySizes.length];
            for (int i = 0; i < arraySizes.length; i++) {
                final int index = i;
                times[index] = results.stream()
                        .filter(r -> r.getAlgorithmName().equals(algo) && r.getDataSize() == arraySizes[index])
                        .mapToDouble(SortingResult::getReverseArrayTime)
                        .average()
                        .orElse(0.0);
            }
            stats.put(algo, times);
        }
        return stats;
    }
}
