package com.coffeecode;

import com.coffeecode.config.SortingConfiguration;
import com.coffeecode.service.SortingBenchmarkService;
import com.coffeecode.view.ResultPrinter;

public class App {

    public static void main(String[] args) {
        // Configure the benchmark
        SortingConfiguration config = new SortingConfiguration.Builder()
                .withArraySizes(100, 1000, 10000)
                .withNumberOfRuns(10)
                .withMemoryStats(true)
                .build();

        // Run the benchmark
        SortingBenchmarkService benchmarkService = new SortingBenchmarkService(config);
        var results = benchmarkService.runBenchmark();

        // Print results
        ResultPrinter.printResults(results, config);
    }
}
