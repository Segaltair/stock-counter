package com.surakin.stock.counter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Результат подсчета акций
 */
@Getter
@Setter
@AllArgsConstructor
public class Response {

    /** Общая стоимость акций */
    private int value;

    /** Распределение по секторам */
    private List<Portfolio> allocations;

}
