package com.wojnarowicz.sfg.recipe.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailySummaryByUserDTO {

    private String userName;
    private BigDecimal nonTransferCredit = BigDecimal.ZERO;
    private BigDecimal nonTransferDebit = BigDecimal.ZERO;
    private BigDecimal transferCredit = BigDecimal.ZERO;
    private BigDecimal transferDebit = BigDecimal.ZERO;
    
    public DailySummaryByUserDTO(IDailySummaryByUser data, boolean isCredit) {
        this.userName = data.getUserName();

        if(isCredit) {
            this.nonTransferCredit = data.getTransferFlag() == 1 ? BigDecimal.ZERO : data.getAmount();
            this.transferCredit = data.getTransferFlag() == 1 ? data.getAmount() : BigDecimal.ZERO;
        } else {
            this.nonTransferDebit = data.getTransferFlag() == 1 ? BigDecimal.ZERO : data.getAmount().multiply(BigDecimal.valueOf(-1));
            this.transferDebit = data.getTransferFlag() == 1 ? data.getAmount().multiply(BigDecimal.valueOf(-1)) : BigDecimal.ZERO;
        }
    }

    public DailySummaryByUserDTO merge(DailySummaryByUserDTO oldVal, DailySummaryByUserDTO newVal) {
        newVal.setNonTransferCredit(oldVal.getNonTransferCredit().add(newVal.getNonTransferCredit()));
        newVal.setNonTransferDebit(oldVal.getNonTransferDebit().add(newVal.getNonTransferDebit()));
        newVal.setTransferCredit(oldVal.getTransferCredit().add(newVal.getTransferCredit()));
        newVal.setTransferDebit(oldVal.getTransferDebit().add(newVal.getTransferDebit()));
        return newVal;
    }

    @Override
    public String toString() {
        return "PivotReportRow [userName=" + userName + ", nonTransferCredit=" + nonTransferCredit
                + ", nonTransferDebit=" + nonTransferDebit + ", transferCredit=" + transferCredit + ", transferDebit="
                + transferDebit + "]";
    }
}
