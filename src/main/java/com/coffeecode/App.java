package com.coffeecode;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.coffeecode.config.SortingConfiguration;
import com.coffeecode.service.SortingBenchmarkService;
import com.coffeecode.statistics.BenchmarkStatistics;
import com.coffeecode.statistics.ChartGenerator;
import com.coffeecode.view.ResultPrinter;

public class App {

    public static void main(String[] args) {
        // Configure the benchmark
        SortingConfiguration config = new SortingConfiguration.Builder()
                .withArraySizes(100, 1000, 10000)
                .withNumberOfRuns(10)
                .withMemoryStats(false)
                .build();

        System.out.println("Starting benchmark...");

        // Create loading animation executor
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable loadingTask = createLoadingAnimation();
        executor.scheduleAtFixedRate(loadingTask, 0, 100, TimeUnit.MILLISECONDS);

        try {
            // Run the benchmark
            SortingBenchmarkService benchmarkService = new SortingBenchmarkService(config);
            var results = benchmarkService.runBenchmark();

            // Stop loading animation
            executor.shutdownNow();

            // Print results
            ResultPrinter.printResults(results, config);

            // Generate statistical charts
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            BenchmarkStatistics stats = new BenchmarkStatistics(results, config.getArraySizes());
            ChartGenerator chartGen = new ChartGenerator(stats, config.getArraySizes());
            chartGen.generateCharts("reports");

            System.out.println("\nCharts generated in 'reports' directory");

        } catch (IOException e) {
            executor.shutdownNow();
            System.err.println("Error during benchmark: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static final char[] LOADING_CHARS = {'|', '/', '-', '\\'};

    private static Runnable createLoadingAnimation() {
        return new Runnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                System.out.print("\rRunning benchmark " + LOADING_CHARS[i % LOADING_CHARS.length]);
                i++;
            }
        };
    }}
