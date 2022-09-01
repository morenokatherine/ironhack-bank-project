package com.bank.services.user;

import com.bank.dto.*;
import com.bank.models.account.*;
import com.bank.models.user.AccountHolder;
import com.bank.models.user.Admin;
import com.bank.repositories.*;
import com.bank.services.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    CheckingService checkingService;
    @Autowired
    StudentCheckingService studentCheckingService;
    @Autowired
    SavingService savingService;
    @Autowired
    CreditCardService creditCardService;

    @Autowired
    AccountService accountService;

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolder){
        return accountHolderService.createAccountHolder(accountHolder);
    }

    public Admin createAdmin(AdminDTO admin){
        Admin adminResult = new Admin();
        adminResult.setName(admin.getName());
        return adminRepository.save(adminResult);
    }

    public Account createAccount(AccountDTO accountDTO){
        if(Period.between(accountDTO.getPrimaryOwner().getBirthday(), LocalDate.now()).getYears() > 24){
            return checkingService.createChecking(accountDTO);
        }else {
            return studentCheckingService.createStudentChecking(accountDTO);
        }}

    public Saving createSavings(SavingsDTO savingsDTO){
        return savingService.createSaving(savingsDTO);
    }

    public CreditCard createCreditCard(CreditCardDTO creditCardDTO){
        return creditCardService.createCreditCard(creditCardDTO);
    }

    public Account getAccountById(int id) {
        return accountService.getAccountById(id);
    }
}
