package com.bank.repositories.account;

import com.bank.models.account.Account;
import com.bank.models.account.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {
    List<Interest> findByAccount(Account account);
}
