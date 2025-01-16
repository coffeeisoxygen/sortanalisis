package com.coffeecode;

import java.util.Map;

import com.coffeecode.comparisons.MemoryTestResult;
import com.coffeecode.comparisons.SortingComparison;

public class App {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        // Initialize with array size
        SortingComparison comparison = new SortingComparison(10000);

        // Run performance tests
        System.out.println(YELLOW + "=== Performance Test ===" + RESET);
        Map<String, Long> timeResults = comparison.measureExecutionTime();
        timeResults.forEach((name, time)
                -> System.out.printf("%s: %s%.4f ms%s%n",
                        name, BLUE, time / 1_000_000.0, RESET));

        // Run memory tests
        System.out.println(YELLOW + "\n=== Memory Usage Analysis ===" + RESET);
        System.out.println("----------------------------------------");
        Map<String, MemoryTestResult> memoryResults = comparison.measureDetailedMemoryUsage();
        memoryResults.forEach((name, result) -> {
            System.out.printf("%s:%n", name);
            System.out.printf("  Used Memory: %s%s%s%n", GREEN, result.getFormattedMemoryUsage(), RESET);
            System.out.printf("  Peak Memory: %s%s%s%n", BLUE, result.getPeakMemoryUsage(), RESET);
            System.out.printf("  Efficiency:  %s%s%s%n", YELLOW, result.getEfficiencyRating(), RESET);
            System.out.println();
        });

        // Visual demonstration with small sample
        System.out.println(YELLOW + "\n=== Visual Demo (10 elements) ===" + RESET);
        Map<String, int[]> demoResults = comparison.visualDemo(10);
        demoResults.forEach((name, array)
                -> System.out.printf("%s: %s%s%s%n",
                        name, RED, java.util.Arrays.toString(array), RESET));
    }
}
