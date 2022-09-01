package com.bank.dto;

import com.bank.enums.Status;
import com.bank.models.user.AccountHolder;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SavingsDTO {
    private BigDecimal balance;
    private int penaltyFree;
    private LocalDate date;
    private Status status;
    private AccountHolder primaryOwner;
    private BigDecimal interestRate;
    private String secretKey;


}
