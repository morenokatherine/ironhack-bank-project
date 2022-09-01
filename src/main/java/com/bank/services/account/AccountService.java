package com.bank.services.account;


import com.bank.dto.UpdateAccountBalanceDTO;
import com.bank.models.account.Account;
import com.bank.models.user.AccountHolder;
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
    AccountHolderRepository accountHolderRepository;
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
            throw new Exception("\n" +
                    "The id number does not exist in the database");
        }
        return accountRepository.findByPrimaryOwner(user.get());
    }
    public List<Account> getAccountsByAccountHolderId(int id) throws Exception {
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(id);
        if (!accountHolder.isPresent()){
            throw new Exception("\n" +
                    "The id number does not exist in the database");
        }
        return accountRepository.findByPrimaryOwner(accountHolder.get());
    }

    public Account getAccountById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new IllegalArgumentException("account not found");
        }
        return account.get();
    }

    public Account updateAccountBalanceById(int id, UpdateAccountBalanceDTO updateAccountBalanceDTO) {
        Account account = getAccountById(id);
        account.setBalance(updateAccountBalanceDTO.getBalance());
        return accountRepository.save(account);
    }

}
