package com.bank.models.user;

import com.bank.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ThirdParty extends User {
    private String hashKey;

    public ThirdParty(String name, String hashKey) {
        super(name, null, null, Role.ROLE_THIRD_PARTY);
        this.hashKey = hashKey;
    }
}
