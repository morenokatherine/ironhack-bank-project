package com.bank.dto;

import com.bank.enums.Status;
import com.bank.models.user.User;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreditCardDTO {

    private BigDecimal balance;
    private int penaltyFree;
    private LocalDate date;
    private Status status;
    private User primaryOwner;
    private BigDecimal interestRate;
    private BigDecimal creditLimit;
}
