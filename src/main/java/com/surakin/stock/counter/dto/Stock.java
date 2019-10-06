package com.surakin.stock.counter.dto;

public class Stock {
    //Аббревиатура
    private String symbol;
    //Сектор
    //Количество акций
    private Integer volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
