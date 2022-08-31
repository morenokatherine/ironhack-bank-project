package com.bank.services;


import com.bank.models.account.Account;
import com.bank.models.user.User;
import com.bank.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    UserRepository userRepository;

    public List<Account> getAccountsByUserId(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new Exception();
        }
        return accountRepository.findByPrimaryOwner(user.get());
    }

}
