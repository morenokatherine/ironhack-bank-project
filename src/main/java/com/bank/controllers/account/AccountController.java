package com.bank.controllers.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.dto.account.UpdateAccountBalanceDTO;
import com.bank.enums.Role;
import com.bank.models.account.Account;
import com.bank.models.user.User;
import com.bank.security.CustomUserDetails;
import com.bank.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        if (user.getRole() == Role.ROLE_ADMIN) {
            return accountService.getAccounts();
        }
        return accountService.getAccountsByUserId(user.getId());
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccountById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable int accountId) {
        User user = customUserDetails.getUser();
        if (user.getRole() == Role.ROLE_ADMIN) {
            return accountService.getAccountById(accountId);
        }
        return accountService.getAccountByIdAndUser(accountId, user);
    }

    @PatchMapping("/accounts/{accountId}")
    public Account updateAccountById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable int accountId, @RequestBody UpdateAccountBalanceDTO updateAccountBalanceDTO) {
        return accountService.updateAccountBalanceById(accountId, updateAccountBalanceDTO);
    }

    @PostMapping("/accounts/checkings")
    public Account createCheckingAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        return accountService.createCheckingAccount(createAccountDTO);
    }
}
