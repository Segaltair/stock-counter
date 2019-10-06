package com.surakin.stock.counter.dto;

import java.util.List;

public class Response {
    private int value;
    private List<Portfolio> allocations;

    public Response(int value, List<Portfolio> allocations) {
        this.value = value;
        this.allocations = allocations;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Portfolio> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Portfolio> allocations) {
        this.allocations = allocations;
    }
}
