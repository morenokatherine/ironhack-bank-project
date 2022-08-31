package com.bank.models.user;

import com.bank.models.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ThirdParty extends User {

    public ThirdParty(String name, List<Account> account) {
        super(name, account);
    }
}
