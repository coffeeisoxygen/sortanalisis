package com.coffeecode.statistics;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartGenerator {

    private final BenchmarkStatistics statistics;
    private final int[] arraySizes;

    public ChartGenerator(BenchmarkStatistics statistics, int[] arraySizes) {
        this.statistics = statistics;
        this.arraySizes = arraySizes;
    }

    public void generateCharts(String outputDirectory) throws IOException {
        generateChart("Random Array Performance", statistics.getRandomArrayStats(),
                outputDirectory + "/random_performance.png");
        generateChart("Sorted Array Performance", statistics.getSortedArrayStats(),
                outputDirectory + "/sorted_performance.png");
        generateChart("Reverse Array Performance", statistics.getReverseArrayStats(),
                outputDirectory + "/reverse_performance.png");
    }

    private void generateChart(String title, Map<String, double[]> data, String outputPath)
            throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (Map.Entry<String, double[]> entry : data.entrySet()) {
            XYSeries series = new XYSeries(entry.getKey());
            for (int i = 0; i < arraySizes.length; i++) {
                series.add(arraySizes[i], entry.getValue()[i]);
            }
            dataset.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Input Size",
                "Execution Time (ms)",
                dataset
        );

        ChartUtils.saveChartAsPNG(new File(outputPath), chart, 800, 400);
    }
}
