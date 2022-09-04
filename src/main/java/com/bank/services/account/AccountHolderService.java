package com.bank.services.account;


import com.bank.dto.user.CreateAccountHolderDTO;
import com.bank.enums.Role;
import com.bank.models.account.Account;
import com.bank.models.user.AccountHolder;
import com.bank.repositories.user.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountService accountService;

    public AccountHolder getAccountHolderById(int id) {
        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(id);
        if (!accountHolder.isPresent()) {
            throw new IllegalArgumentException("account holder not found");
        }
        return accountHolder.get();
    }


    public AccountHolder createAccountHolder(CreateAccountHolderDTO accountHolderDTO){
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setAddress(accountHolderDTO.getAddress());
        accountHolder.setPassword(accountHolderDTO.getPassword());
        accountHolder.setBirthday(accountHolderDTO.getBirthday());
        accountHolder.setRole(Role.ROLE_ACCOUNT_HOLDER);
        return accountHolderRepository.save(accountHolder);
    }

    public List<Account> getAccounts(int accountHolderId){
        AccountHolder accountHolder = getAccountHolderById(accountHolderId);
        return accountService.getAccountsByAccountHolder(accountHolder);
    }

    public Account logIn(int idAccount, int idAccountHolder, String password){
        Account account = accountService.getAccountById(idAccount);
        AccountHolder accountHolder = getAccountHolderById(idAccountHolder);
        return null;
    }
}
