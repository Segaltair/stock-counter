package com.surakin.stock.counter.dto;

import lombok.Data;

import java.util.Arrays;

@Data
public class ApiResponse {
    private Double value;
    private Portfolio[] allocations;

    public ApiResponse(Portfolio[] allocations) {
        this.allocations = allocations;
        value = Arrays.stream(allocations)
                .mapToDouble(Portfolio::getAssetValue)
                .sum();
    }
}
