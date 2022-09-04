package com.bank.dto.account;

import com.bank.models.account.Account;
import lombok.*;

import javax.validation.constraints.Digits;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransferDTO {
    private int originAccountId;
    private int destinationAccountId;
    @Digits(integer = 6, fraction = 2)
    private double amount;
}
