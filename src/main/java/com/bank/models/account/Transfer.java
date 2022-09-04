package com.bank.models.account;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn
    private Account originAccount;
    @ManyToOne
    @JoinColumn
    private Account destinationAccount;
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;
    private final LocalDate transferDate = LocalDate.now();

    public Transfer(Account originAccount, Account destinationAccount, BigDecimal amount) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }
}
