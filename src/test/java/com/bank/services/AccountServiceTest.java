package com.bank.services;

import com.bank.enums.Status;
import com.bank.models.Address;
import com.bank.models.account.Account;
import com.bank.models.account.Checking;
import com.bank.models.user.AccountHolder;
import com.bank.repositories.AccountHolderRepository;
import com.bank.repositories.CheckingRepository;
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


    @Test
    @DisplayName("Test a getAccountsByUserId")
    public void getAccountsByUserId_Test(){

        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe",accounts,address,"1234",LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        Checking checking = new Checking(new BigDecimal(50).setScale(2), 10,LocalDate.now(),Status.ACTIVE,accountHolder1,LocalDate.now(),
                new BigDecimal(0).setScale(2),"123");
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





    }


}
