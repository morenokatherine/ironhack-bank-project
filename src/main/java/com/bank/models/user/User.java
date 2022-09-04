package com.bank.models.user;

import com.bank.enums.Role;
import com.bank.models.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner", fetch = FetchType.LAZY)
    private List<Account> account;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String password, List<Account> account, Role role) {
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.account = account;
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
