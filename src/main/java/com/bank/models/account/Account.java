package com.bank.models.account;

import com.bank.enums.Status;
import com.bank.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    private final int penaltyFee = 40;
    private LocalDate date;
    private Status status;

    @ManyToOne
    @JoinColumn
    private User primaryOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "originAccount")
    List<Transfer> transfersSent;

    @JsonIgnore
    @OneToMany(mappedBy = "destinationAccount")
    List<Transfer> transfersReceived;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Interest> interest;

    public Account(BigDecimal balance, LocalDate date, Status status, User primaryOwner) {
        this.balance = balance;
        this.date = date;
        this.status = status;
        this.primaryOwner = primaryOwner;
    }
}
