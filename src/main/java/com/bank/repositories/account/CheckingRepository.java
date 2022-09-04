package com.bank.repositories.account;

import com.bank.models.account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
