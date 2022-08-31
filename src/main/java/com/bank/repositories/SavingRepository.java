package com.bank.repositories;

import com.bank.models.account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Savings, Integer> {
}
