package com.coffeecode.results;

public class MemoryTestResult {

    private final long memoryUsed;

    public MemoryTestResult(long memoryUsed) {

        this.memoryUsed = memoryUsed;

    }

    public long getMemoryUsed() {

        return memoryUsed;

    }

}
