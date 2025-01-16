package com.coffeecode.results;

public class SortingResult {

    private final String algorithmName;
    private final int dataSize;
    private final double randomArrayTime;
    private final double sortedArrayTime;
    private final double reverseArrayTime;
    private final double memoryUsed;

    public static class Builder {

        private String algorithmName;
        private int dataSize;
        private double randomArrayTime;
        private double sortedArrayTime;
        private double reverseArrayTime;
        private double memoryUsed;

        // Add builder methods
        public Builder withAlgorithm(String name) {
            this.algorithmName = name;
            return this;
        }

        public Builder withDataSize(int size) {
            this.dataSize = size;
            return this;
        }

        public Builder withRandomArrayTime(double time) {
            this.randomArrayTime = time;
            return this;
        }

        public Builder withSortedArrayTime(double time) {
            this.sortedArrayTime = time;
            return this;
        }

        public Builder withReverseArrayTime(double time) {
            this.reverseArrayTime = time;
            return this;
        }

        public Builder withMemoryUsed(double memory) {
            this.memoryUsed = memory;
            return this;
        }

        public SortingResult build() {
            return new SortingResult(this);
        }
    }

    private SortingResult(Builder builder) {
        this.algorithmName = builder.algorithmName;
        this.dataSize = builder.dataSize;
        this.randomArrayTime = builder.randomArrayTime;
        this.sortedArrayTime = builder.sortedArrayTime;
        this.reverseArrayTime = builder.reverseArrayTime;
        this.memoryUsed = builder.memoryUsed;
    }

    // Getters
    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getDataSize() {
        return dataSize;
    }

    public double getRandomArrayTime() {
        return randomArrayTime;
    }

    public double getSortedArrayTime() {
        return sortedArrayTime;
    }

    public double getReverseArrayTime() {
        return reverseArrayTime;
    }

    public double getMemoryUsed() {
        return memoryUsed;
    }
}
