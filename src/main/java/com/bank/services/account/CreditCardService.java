package com.bank.services.account;

import com.bank.dto.account.CreateCreditCardDTO;
import com.bank.models.account.CreditCard;
import com.bank.repositories.account.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreateCreditCardDTO createCreditCardDTO) {
        if (createCreditCardDTO.getInterestRate().compareTo(BigDecimal.valueOf(0.1)) < 0) {
            throw new IllegalArgumentException("interestRate must not be less than 0.1");
        }
        if (createCreditCardDTO.getCreditLimit().compareTo(BigDecimal.valueOf(100)) < 0 || createCreditCardDTO.getCreditLimit().compareTo(BigDecimal.valueOf(100000)) > 0) {
            throw new IllegalArgumentException("the credit limit must be between 100 and 100.000");
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(createCreditCardDTO.getBalance());
        creditCard.setDate(createCreditCardDTO.getDate());
        creditCard.setStatus(createCreditCardDTO.getStatus());
        creditCard.setPrimaryOwner(createCreditCardDTO.getPrimaryOwner());
        creditCard.setInterestRate(createCreditCardDTO.getInterestRate());
        creditCard.setCreditLimit(createCreditCardDTO.getCreditLimit());
        return creditCardRepository.save(creditCard);
    }
}
