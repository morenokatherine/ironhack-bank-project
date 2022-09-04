package com.bank.models.account;

import com.bank.enums.Status;
import com.bank.models.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Checking extends Account {
    private final BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12).setScale(2);

    private final BigDecimal minimumBalance = BigDecimal.valueOf(250).setScale(2);
    private String secretKey;

    public Checking(BigDecimal balance, LocalDate date, Status status, User primaryOwner, String secretKey) {
        super(balance, date, status, primaryOwner);
        this.secretKey = secretKey;
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
