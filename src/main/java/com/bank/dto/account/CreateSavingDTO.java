package com.bank.dto.account;

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
public class CreateSavingDTO {
    private BigDecimal balance;
    private LocalDate date;
    private Status status;
    private AccountHolder primaryOwner;
    private BigDecimal interestRate;
    private String secretKey;


}
