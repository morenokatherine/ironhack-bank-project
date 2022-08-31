package com.bank.models.account;

import com.bank.enums.Status;
import com.bank.models.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CreditCard extends Account {

    private BigDecimal interestRate;
    private BigDecimal creditLimit;

    public CreditCard(BigDecimal balance, int penaltyFree, LocalDate date, Status status, User primaryOwner, BigDecimal interestRate, BigDecimal creditLimit) {
        super(balance, penaltyFree, date, status, primaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }
}
