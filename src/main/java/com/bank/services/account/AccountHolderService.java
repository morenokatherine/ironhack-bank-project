package com.bank.services.account;


import com.bank.dto.AccountHolderDTO;
import com.bank.models.user.AccountHolder;
import com.bank.repositories.AccountHolderRepository;
import com.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;


    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO){
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setAddress(accountHolderDTO.getAddress());
        accountHolder.setPassword(accountHolderDTO.getPassword());
        accountHolder.setBirthday(accountHolderDTO.getBirthday());
        return accountHolderRepository.save(accountHolder);
}
}
