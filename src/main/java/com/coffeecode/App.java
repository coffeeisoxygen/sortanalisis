package com.coffeecode;

import java.util.Map;

import com.coffeecode.comparisons.MemoryTestResult;
import com.coffeecode.comparisons.SortingComparison;

public class App {
    private static final class ConsoleColors {
        private static final String RESET = "\u001B[0m";
        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[32m";
        private static final String YELLOW = "\u001B[33m";
        private static final String BLUE = "\u001B[34m";
        
        private ConsoleColors() {} // Prevent instantiation
    }

    private static final int DEFAULT_ARRAY_SIZE = 10000;
    private static final int DEMO_SIZE = 10;
    
    public static void main(String[] args) {
        SortingComparison comparison = new SortingComparison(DEFAULT_ARRAY_SIZE);
        
        runPerformanceTests(comparison);
        runMemoryTests(comparison);
        runVisualDemo(comparison);
    }

    private static void runPerformanceTests(SortingComparison comparison) {
        System.out.println(ConsoleColors.YELLOW + "=== Performance Test ===" + ConsoleColors.RESET);
        Map<String, Long> timeResults = comparison.measureExecutionTime();
        timeResults.forEach((name, time) -> printExecutionTime(name, time));
    }

    private static void runMemoryTests(SortingComparison comparison) {
        System.out.println(ConsoleColors.YELLOW + "\n=== Memory Usage Analysis ===" + ConsoleColors.RESET);
        System.out.println("----------------------------------------");
        Map<String, MemoryTestResult> memoryResults = comparison.measureDetailedMemoryUsage();
        memoryResults.forEach(App::printMemoryResult);
    }

    private static void runVisualDemo(SortingComparison comparison) {
        System.out.println(ConsoleColors.YELLOW + "\n=== Visual Demo (10 elements) ===" + ConsoleColors.RESET);
        Map<String, int[]> demoResults = comparison.visualDemo(DEMO_SIZE);
        demoResults.forEach(App::printDemoResult);
    }

    private static void printExecutionTime(String name, Long time) {
        System.out.printf("%s: %s%.4f ms%s%n",
            name, 
            ConsoleColors.BLUE, 
            time / 1_000_000.0, 
            ConsoleColors.RESET);
    }

    private static void printMemoryResult(String name, MemoryTestResult result) {
        System.out.printf("%s:%n", name);
        System.out.printf("  Used Memory: %s%s%s%n", 
            ConsoleColors.GREEN, 
            result.getFormattedMemoryUsage(), 
            ConsoleColors.RESET);
        System.out.printf("  Peak Memory: %s%s%s%n", 
            ConsoleColors.BLUE, 
            result.getPeakMemoryUsage(), 
            ConsoleColors.RESET);
        System.out.printf("  Efficiency:  %s%s%s%n", 
            ConsoleColors.YELLOW, 
            result.getEfficiencyRating(), 
            ConsoleColors.RESET);
        System.out.println();
    }

    private static void printDemoResult(String name, int[] array) {
        System.out.printf("%s: %s%s%s%n",
            name, 
            ConsoleColors.RED, 
            java.util.Arrays.toString(array), 
            ConsoleColors.RESET);
    }
}
