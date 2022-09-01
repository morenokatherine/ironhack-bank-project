package com.bank.services.account;

import com.bank.dto.CreditCardDTO;
import com.bank.models.account.CreditCard;
import com.bank.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreditCardDTO creditCardDTO) {
        if (creditCardDTO.getInterestRate().compareTo(BigDecimal.valueOf(0.1)) < 0) {
            throw new IllegalArgumentException("el interest rate no debe ser menor que 0.1");
        }
        if (creditCardDTO.getCreditLimit().compareTo(BigDecimal.valueOf(100)) < 0 || creditCardDTO.getCreditLimit().compareTo(BigDecimal.valueOf(100000)) > 0) {
            throw new IllegalArgumentException("el credit limit debe estar entre 100 y 100.000");
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(creditCardDTO.getBalance());
        creditCard.setPenaltyFree(creditCard.getPenaltyFree());
        creditCard.setDate(creditCardDTO.getDate());
        creditCard.setStatus(creditCardDTO.getStatus());
        creditCard.setPrimaryOwner(creditCardDTO.getPrimaryOwner());
        creditCard.setInterestRate(creditCardDTO.getInterestRate());
        creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
        return creditCardRepository.save(creditCard);
    }
}
