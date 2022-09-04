package com.bank.dto.account;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateAccountBalanceDTO {
    private BigDecimal balance;
}
