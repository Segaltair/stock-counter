package com.surakin.stock.counter.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Котировка
 */
@Getter
@Setter
public class Quote {

    /** Текущая стоимость */
    private BigDecimal latestPrice;

    /** Название сектора */
    private String sector;

}
