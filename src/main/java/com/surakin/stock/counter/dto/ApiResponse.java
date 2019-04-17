package com.surakin.stock.counter.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private Double value;
    private List<Portfolio> allocations;

    public ApiResponse(Double value, List<Portfolio> allocations) {
        this.value = value;
        this.allocations = allocations;
    }
}
