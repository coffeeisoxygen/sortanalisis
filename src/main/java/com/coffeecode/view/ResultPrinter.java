package com.coffeecode.view;

import java.util.List;

import com.coffeecode.config.SortingConfiguration;
import com.coffeecode.results.SortingResult;

public class ResultPrinter {

    public static void printResults(List<SortingResult> results, SortingConfiguration config) {
        System.out.println("Sorting Algorithm Performance Analysis");
        System.out.println("Number of runs: " + config.getNumberOfRuns());
        System.out.println("----------------------------------------");

        String format = "%-12s\t%-8d\t%-10.2f\t%-10.2f\t%-10.2f";
        System.out.println("Algorithm\tSize\tRandom(ms)\tSorted(ms)\tReverse(ms)");

        for (SortingResult result : results) {
            System.out.printf(format + "%n",
                    result.getAlgorithmName(),
                    result.getDataSize(),
                    result.getRandomArrayTime(),
                    result.getSortedArrayTime(),
                    result.getReverseArrayTime());
        }

        if (config.includeMemoryStats()) {
            System.out.println("\nMemory Usage (bytes):");
            results.forEach(r
                    -> System.out.printf("%s (%d): %.2f%n",
                            r.getAlgorithmName(),
                            r.getDataSize(),
                            r.getMemoryUsed()));
        }
    }
}
