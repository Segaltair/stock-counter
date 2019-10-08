package com.surakin.stock.counter.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Акция
 */
@Getter
@Setter
public class Stock {

    /** Название акции */
    private String symbol;

    /** Количество акций */
    private int volume;

}
