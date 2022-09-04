package com.bank.controllers.account;

import com.bank.dto.account.CreateCreditCardDTO;
import com.bank.models.account.Account;
import com.bank.services.account.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @PostMapping("/accounts/credit-cards")
    public Account createCreditCard(@RequestBody CreateCreditCardDTO createCreditCardDTO) {
        return creditCardService.createCreditCard(createCreditCardDTO);
    }
}
