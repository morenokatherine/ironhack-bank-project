package com.bank.models.account;

import com.bank.enums.Status;
import com.bank.models.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class StudentChecking extends Account {

    private String secretKey;

    public StudentChecking(BigDecimal balance, LocalDate date, Status status, User primaryOwner, String secretKey) {
        super(balance, date, status, primaryOwner);
        this.secretKey = secretKey;
    }
}
