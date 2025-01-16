package com.coffeecode.statistics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartGenerator {

    private final BenchmarkStatistics statistics;
    private final int[] arraySizes;
    private final Color[] CHART_COLORS = {
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.ORANGE
    };

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
        int seriesIndex = 0;

        for (Map.Entry<String, double[]> entry : data.entrySet()) {
            XYSeries series = new XYSeries(entry.getKey());
            for (int i = 0; i < arraySizes.length; i++) {
                series.add(arraySizes[i], entry.getValue()[i]);
            }
            dataset.addSeries(series);
            seriesIndex++;
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Input Size",
                "Execution Time (ms)",
                dataset
        );

        // Customize the plot
        XYPlot plot = (XYPlot) chart.getPlot();

        // Set logarithmic axes
        LogarithmicAxis xAxis = new LogarithmicAxis("Input Size");
        LogarithmicAxis yAxis = new LogarithmicAxis("Execution Time (ms)");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        // Customize renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesPaint(i, CHART_COLORS[i % CHART_COLORS.length]);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
            renderer.setSeriesShapesVisible(i, true);
        }
        plot.setRenderer(renderer);

        // Customize appearance
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);

        // Save the chart
        ChartUtils.saveChartAsPNG(new File(outputPath), chart, 800, 600);
    }
}
