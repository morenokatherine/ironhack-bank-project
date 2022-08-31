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

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal balance;

    private int penaltyFree;
    private LocalDate date;
    private Status status;

    @ManyToOne
    @JoinColumn
    private User primaryOwner;

    public Account(BigDecimal balance, int penaltyFree, LocalDate date, Status status, User primaryOwner) {
        this.balance = balance;
        this.penaltyFree = penaltyFree;
        this.date = date;
        this.status = status;
        this.primaryOwner = primaryOwner;
    }
}
