package com.lothuialon.domain.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class money {
    
    public static final money ZERO = new money(BigDecimal.ZERO);
    private final BigDecimal amount;


    //comparison methods
    public boolean isGreaterThan(money money){
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public boolean isGreaterThanZero(){
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }
    //calculations
    public money add(money money){
        return new money(decimalSetting(this.amount.add(money.getAmount())));
    }

    public money subtract(money money){
        return new money(decimalSetting(this.amount.subtract(money.getAmount())));
    }

    public money multiply(int multiplier) {
        return new money(decimalSetting(this.amount.multiply(new BigDecimal(multiplier))));
    }
    //for precise calculations remove repeating decimals
    private BigDecimal decimalSetting(BigDecimal input){
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }

    public money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof money)) {
            return false;
        }
        money money = (money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
    
}
