package com.bank.services.account;


import com.bank.dto.account.CreateAccountDTO;
import com.bank.dto.account.UpdateAccountBalanceDTO;
import com.bank.models.account.*;
import com.bank.models.user.AccountHolder;
import com.bank.models.user.User;
import com.bank.repositories.account.AccountRepository;
import com.bank.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;

    @Autowired
    InterestService interestService;

    @Autowired
    StudentCheckingService studentCheckingService;
    @Autowired
    CheckingService checkingService;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByUserId(int id) {
        User user = userService.getUserById(id);
        return accountRepository.findByPrimaryOwner(user);
    }
    public List<Account> getAccountsByAccountHolder(AccountHolder accountHolder) {
        return accountRepository.findByPrimaryOwner(accountHolder);
    }

    public Account getAccountById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new IllegalArgumentException("account not found");
        }
        checkInterestByAccount(account.get());
        return account.get();
    }

    public Account createCheckingAccount(CreateAccountDTO createAccountDTO){
        if(Period.between(createAccountDTO.getPrimaryOwner().getBirthday(), LocalDate.now()).getYears() > 24){
            return checkingService.createChecking(createAccountDTO);
        }else {
            return studentCheckingService.createStudentChecking(createAccountDTO);
        }
    }

    public void checkInterestByAccount(Account account) {
        List<Interest> interests = interestService.getInterestsByAccount(account);

        if (account instanceof Saving) {
            int pendingInterest = Period.between(account.getDate(), LocalDate.now()).getYears() - interests.size();
            if (pendingInterest > 0) {
                for (int i = 0; i < pendingInterest; i++) {
                    BigDecimal balanceWithInterest = account.getBalance().multiply(((Saving) account).getInterestRate().add(BigDecimal.valueOf(1)));
                    account.setBalance(balanceWithInterest);
                    interestService.addInterest(account);
                }
                accountRepository.save(account);
            }

        }

        if (account instanceof CreditCard) {
            Period period = Period.between(account.getDate(), LocalDate.now());
            int pendingInterest = period.getYears() * 12 + period.getMonths() - interests.size();
            if (pendingInterest > 0) {
                for (int i = 0; i < pendingInterest; i++) {
                    BigDecimal balanceWithInterest = account.getBalance().multiply(((CreditCard) account).getInterestRate().add(BigDecimal.valueOf(1)));
                    account.setBalance(balanceWithInterest);
                    interestService.addInterest(account);
                }
                accountRepository.save(account);
            }
        }

    }

    public Account updateAccountBalanceById(int id, UpdateAccountBalanceDTO updateAccountBalanceDTO) {
        Account account = getAccountById(id);
        account.setBalance(updateAccountBalanceDTO.getBalance());
        return accountRepository.save(account);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountByIdAndUser(int accountId, User user) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            throw new IllegalArgumentException("account not found");
        }
        if (account.get().getPrimaryOwner().getId() != user.getId()) {
            throw new IllegalArgumentException("unauthorized");
        }
        checkInterestByAccount(account.get());
        return account.get();
    }
}
