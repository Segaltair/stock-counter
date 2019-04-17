package com.surakin.stock.counter.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiRequest {

    private List<Stock> stocks;

}
