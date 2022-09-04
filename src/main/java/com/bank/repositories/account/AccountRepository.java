package com.bank.repositories.account;

import com.bank.models.account.Account;
import com.bank.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByPrimaryOwner(User user);
}
