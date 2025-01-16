package com.coffeecode.config;

public class SortingConfiguration {

    private final int[] arraySizes;
    private final int numberOfRuns;
    private final boolean includeMemoryStats;

    public static class Builder {

        private int[] arraySizes = {100, 1000, 10000};
        private int numberOfRuns = 1;
        private boolean includeMemoryStats = false;

        public Builder withArraySizes(int... sizes) {
            this.arraySizes = sizes;
            return this;
        }

        public Builder withNumberOfRuns(int runs) {
            this.numberOfRuns = runs;
            return this;
        }

        public Builder withMemoryStats(boolean include) {
            this.includeMemoryStats = include;
            return this;
        }

        public SortingConfiguration build() {
            return new SortingConfiguration(this);
        }
    }

    private SortingConfiguration(Builder builder) {
        this.arraySizes = builder.arraySizes;
        this.numberOfRuns = builder.numberOfRuns;
        this.includeMemoryStats = builder.includeMemoryStats;
    }

    public int[] getArraySizes() {
        return arraySizes;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public boolean includeMemoryStats() {
        return includeMemoryStats;
    }
}
