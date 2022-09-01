package com.bank.models.user;

import com.bank.models.utils.Address;
import com.bank.models.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AccountHolder extends User {
    @Embedded
    private Address address;
    private String password;
    private LocalDate birthday;

    public AccountHolder(String name, List<Account> account, Address primaryAddress, String password, LocalDate birthday) {
        super(name, account);
        this.address = primaryAddress;
        this.password = password;
        this.birthday = birthday;
    }
}
