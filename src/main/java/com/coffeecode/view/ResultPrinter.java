package com.coffeecode.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.coffeecode.config.SortingConfiguration;
import com.coffeecode.results.SortingResult;

public class ResultPrinter {

    public static void printResults(List<SortingResult> results, SortingConfiguration config) {
        System.out.println("\nSorting Algorithm Performance Analysis");
        System.out.println("Number of runs: " + config.getNumberOfRuns());
        System.out.println("----------------------------------------");

        // Group results by size
        Map<Integer, List<SortingResult>> resultsBySize = results.stream()
                .collect(Collectors.groupingBy(SortingResult::getDataSize));

        // Print header
        System.out.printf("%-12s %-8s %-12s %-12s %-12s%n",
                "Algorithm", "Size", "Random(ms)", "Sorted(ms)", "Reverse(ms)");
        System.out.println("----------------------------------------------------------");

        // Print results grouped by size
        resultsBySize.keySet().stream()
                .sorted()
                .forEach(size -> {
                    List<SortingResult> sizeResults = resultsBySize.get(size);
                    sizeResults.forEach(result -> {
                        System.out.printf("%-12s %-8d %-12.2f %-12.2f %-12.2f%n",
                                result.getAlgorithmName(),
                                result.getDataSize(),
                                result.getRandomArrayTime(),
                                result.getSortedArrayTime(),
                                result.getReverseArrayTime());
                    });
                    System.out.println("----------------------------------------------------------");
                });

        if (config.includeMemoryStats()) {
            printMemoryStats(results);
        }
    }

    private static void printMemoryStats(List<SortingResult> results) {
        System.out.println("\nMemory Usage Analysis (bytes)");
        System.out.println("----------------------------------------");
        results.forEach(r -> System.out.printf("%-12s (size: %-8d): %.2f%n",
                r.getAlgorithmName(),
                r.getDataSize(),
                r.getMemoryUsed()));
    }
}
