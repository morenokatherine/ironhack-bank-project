package com.bank.models.user;

import com.bank.enums.Role;
import com.bank.models.utils.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AccountHolder extends User {
    @Embedded
    private Address address;
    private LocalDate birthday;

    public AccountHolder(String name, String password, Address address, LocalDate birthday) {
        super(name, password, new ArrayList<>(), Role.ROLE_ACCOUNT_HOLDER);
        this.address = address;
        this.birthday = birthday;
    }
}
