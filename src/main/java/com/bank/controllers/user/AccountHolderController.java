package com.bank.controllers.user;

import com.bank.dto.user.CreateAccountHolderDTO;
import com.bank.models.user.AccountHolder;
import com.bank.services.account.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @PostMapping("/users/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody CreateAccountHolderDTO createAccountHolderDTO){
        return accountHolderService.createAccountHolder(createAccountHolderDTO);
    }
}
