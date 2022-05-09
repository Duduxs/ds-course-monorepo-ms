package com.edudev.mspayroll.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {

    private String name;
    private BigDecimal dailyIncome;
    private Integer days;

    public Payment(){}

    public Payment(String name, BigDecimal dailyIncome, Integer days) {
        this.name = name;
        this.dailyIncome = dailyIncome;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getTotal() {
        return dailyIncome.multiply(BigDecimal.valueOf(days));
    }
}
