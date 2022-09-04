package com.bank.dto.account;

import lombok.*;

import javax.validation.constraints.Digits;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SendExternalTransferDTO {
    private int accountId;
    @Digits(integer = 6, fraction = 2)
    private double amount;

}
