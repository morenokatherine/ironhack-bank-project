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
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Checking extends Account {
    private LocalDate monthlyMaintenanceFee;
    private BigDecimal minimumBalance;
    private String secretKey;

    public Checking(BigDecimal balance, int penaltyFree, LocalDate date, Status status, User primaryOwner, LocalDate monthlyMaintenanceFee, BigDecimal minimumBalance, String secretKey) {
        super(balance, penaltyFree, date, status, primaryOwner);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
        this.secretKey = secretKey;
    }
}
