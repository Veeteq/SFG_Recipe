package com.wojnarowicz.sfg.recipe.dto;

import java.math.BigDecimal;

public interface IDailySummaryByUser {

    Integer getTransferFlag();
    String getUserName();
    BigDecimal getAmount();
}
