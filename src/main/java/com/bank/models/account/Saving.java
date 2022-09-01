package com.bank.models.account;

import com.bank.enums.Status;
import com.bank.models.user.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Saving extends Account {
    @DecimalMax("0.5")
    @Min(0)
    private BigDecimal interestRate;

    private BigDecimal minimumBalance;
    private String secretKey;



    public Saving(BigDecimal balance, int penaltyFree, LocalDate date, Status status, User primaryOwner, BigDecimal interestRate, BigDecimal minimumBalance, String secretKey) {
        super(balance, penaltyFree, date, status, primaryOwner);
        this.interestRate = interestRate;
        this.interestRate = interestRate != null ? interestRate : BigDecimal.valueOf(0.0025);
        this.minimumBalance = minimumBalance != null ? minimumBalance : BigDecimal.valueOf(1000);
        this.secretKey = secretKey;
    }
}
