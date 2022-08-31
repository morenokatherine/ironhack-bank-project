package com.bank.repositories;


import com.bank.models.account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository <CreditCard, Integer> {
}
