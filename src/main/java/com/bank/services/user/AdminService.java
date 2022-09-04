package com.bank.services.user;

import com.bank.dto.account.CreateCreditCardDTO;
import com.bank.dto.account.CreateSavingDTO;
import com.bank.dto.account.UpdateAccountBalanceDTO;
import com.bank.dto.user.CreateAccountHolderDTO;
import com.bank.dto.user.CreateAdminDTO;
import com.bank.dto.user.CreateThirdPartyDTO;
import com.bank.enums.Role;
import com.bank.models.account.*;
import com.bank.models.user.AccountHolder;
import com.bank.models.user.Admin;
import com.bank.models.user.ThirdParty;
import com.bank.repositories.user.AdminRepository;
import com.bank.services.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    SavingService savingService;
    @Autowired
    CreditCardService creditCardService;

    @Autowired
    AccountService accountService;

    @Autowired
    ThirdPartyService thirdPartyService;

    public AccountHolder createAccountHolder(CreateAccountHolderDTO accountHolder){
        return accountHolderService.createAccountHolder(accountHolder);
    }

    public ThirdParty createThirdParty(CreateThirdPartyDTO createThirdPartyDTO){
        return thirdPartyService.createThirdParty(createThirdPartyDTO);
    }

    public Admin createAdmin(CreateAdminDTO admin){
        Admin adminResult = new Admin();
        adminResult.setName(admin.getName());
        adminResult.setPassword(admin.getPassword());
        adminResult.setRole(Role.ROLE_ADMIN);
        return adminRepository.save(adminResult);
    }



    public Saving createSavingsAccount(CreateSavingDTO createSavingDTO){
        return savingService.createSaving(createSavingDTO);
    }

    public CreditCard createCreditCardAccount(CreateCreditCardDTO createCreditCardDTO){
        return creditCardService.createCreditCard(createCreditCardDTO);
    }

    public Account getAccountById(int id) {
        return accountService.getAccountById(id);
    }
    public Account updateAccountById(int id, UpdateAccountBalanceDTO updateAccountBalanceDTO) {
        return accountService.updateAccountBalanceById(id, updateAccountBalanceDTO);
    }
}
