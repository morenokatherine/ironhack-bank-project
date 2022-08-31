package com.bank.models.user;

import com.bank.models.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner", fetch = FetchType.LAZY)
    private List<Account> account;

    public User(String name, List<Account> account) {
        this.name = name;
        this.account = account;
    }
}
