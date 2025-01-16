package com.coffeecode.comparisons;

public class MemoryTestResult {

    private final String algorithmName;
    private final long bytesUsed;
    private final long peakMemory;

    public MemoryTestResult(String algorithmName, long bytesUsed, long peakMemory) {
        this.algorithmName = algorithmName;
        this.bytesUsed = bytesUsed;
        this.peakMemory = peakMemory;
    }

    public String getFormattedMemoryUsage() {
        if (bytesUsed < 1024) {
            return String.format("%.2f B", (double) bytesUsed);
        } else if (bytesUsed < 1024 * 1024) {
            return String.format("%.2f KB", bytesUsed / 1024.0);
        } else if (bytesUsed < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytesUsed / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", bytesUsed / (1024.0 * 1024.0 * 1024.0));
        }
    }

    public String getPeakMemoryUsage() {
        return String.format("%.2f MB", peakMemory / (1024.0 * 1024.0));
    }

    public String getEfficiencyRating() {
        if (bytesUsed < 1024 * 10) {
            return "Excellent (In-Place)";
        }
        if (bytesUsed < 1024 * 1024) {
            return "Good";
        }
        if (bytesUsed < 1024 * 1024 * 10) {
            return "Fair";
        }
        return "Poor";
    }
}
