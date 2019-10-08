package com.surakin.stock.counter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Обертка для списка акций
 */
@Getter
@Setter
public class Request {

    /** Список акций */
    private List<Stock> stocks;

}
