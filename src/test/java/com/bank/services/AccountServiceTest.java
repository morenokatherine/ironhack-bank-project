package com.bank.services;

import com.bank.enums.Status;
import com.bank.models.user.Admin;
import com.bank.models.utils.Address;
import com.bank.models.account.Account;
import com.bank.models.account.Checking;
import com.bank.models.user.AccountHolder;
import com.bank.repositories.user.AccountHolderRepository;
import com.bank.repositories.user.AdminRepository;
import com.bank.repositories.account.CheckingRepository;
import com.bank.services.account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AdminRepository adminRepository;


    @Test
    @DisplayName("Test a getAccountsByUserId")
    public void getAccountsByUserId_Test(){

        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe","1234",address,LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        Checking checking = new Checking(new BigDecimal(50).setScale(2),LocalDate.now(),Status.ACTIVE,accountHolder1,"123");
        checkingRepository.save(checking);
        List<Account> result = null;
        try {
            result = accountService.getAccountsByUserId(accountHolder1.getId());
            System.out.println(result);
        }catch (Exception e){
            System.err.println(e);
        }

        assertEquals(1, result.size());
        assertTrue(checking.equals(result.get(0)));
        assertThrows(Exception.class, () -> {accountService.getAccountsByUserId(2000);}
        );
    }



    @Test
    @DisplayName("Test that getAccountsByAccountHolder method exception works")
    public void getAccountsByAccountHolderId2_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe","1234",address,LocalDate.now());
        Admin admin = new Admin("pepe", "123");
        adminRepository.save(admin);
        Checking checking = new Checking(new BigDecimal(50).setScale(2),LocalDate.now(),Status.ACTIVE,admin,"123");
        checkingRepository.save(checking);
        assertThrows(Exception.class, () -> {accountService.getAccountsByAccountHolder(accountHolder1);}
        );
    }


}
