package com.surakin.stock.counter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Информация о секторе
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class Portfolio {

    /** Название сектора */
    private String sector;

    /** Стоимость акции в составе портфеля */
    private BigDecimal assetValue;

    /** Процентное соотношение стоимости акций для данного сектора */
    private BigDecimal proportion;

}
