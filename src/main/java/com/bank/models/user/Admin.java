package com.bank.models.user;

import com.bank.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Admin extends User {

    public Admin(String name, String password) {
        super(name, password, new ArrayList<>(), Role.ROLE_ADMIN);
    }

}

