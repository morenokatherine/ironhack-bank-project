package com.bank.models.account;


import lombok.*;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Saving extends Account {
    @DecimalMax("0.5")
    @Min(0)
    private BigDecimal interestRate;

    private BigDecimal minimumBalance;
    private String secretKey;

    public Saving(BigDecimal interestRate, BigDecimal minimumBalance, String secretKey) {
        this.interestRate = interestRate != null ? interestRate : BigDecimal.valueOf(0.0025);
        this.minimumBalance = minimumBalance != null ? minimumBalance : BigDecimal.valueOf(1000);
        this.secretKey = secretKey;
    }

    public Saving() {
        this.interestRate = BigDecimal.valueOf(0.0025);
        this.minimumBalance = BigDecimal.valueOf(1000);
    }

    @Override
    public void setBalance(BigDecimal balance) {
        BigDecimal newBalance = balance;
        if (balance.compareTo(minimumBalance) < 0) {
            newBalance = balance.subtract(BigDecimal.valueOf(super.getPenaltyFee()));
        }
        super.setBalance(newBalance);
    }
}

