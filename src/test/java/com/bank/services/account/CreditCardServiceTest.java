package com.bank.services.account;

import com.bank.dto.CreditCardDTO;
import com.bank.enums.Status;
import com.bank.models.account.Account;
import com.bank.models.account.CreditCard;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import com.bank.repositories.AccountHolderRepository;
import com.bank.services.user.AdminService;
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
public class CreditCardServiceTest {
    @Autowired
    AdminService adminService;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Test
    @DisplayName("Test the createCreditCard method")
    public void createCreditCard_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle primera","Madrid");
        AccountHolder accountHolder1 = new AccountHolder("Coco",accounts,address,"1234", LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreditCardDTO creditCardDTO = new CreditCardDTO(BigDecimal.valueOf(200.00).setScale(2), 10, LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.5).setScale(2),BigDecimal.valueOf(200));
        CreditCard result = null;
        try {
           result = creditCardService.createCreditCard(creditCardDTO);
        }catch (Exception e) {
            System.err.println(e);
        }

       assertTrue(creditCardDTO.getPrimaryOwner().equals(result.getPrimaryOwner()));


    }

    @Test
    @DisplayName("Test that the exception of the createCreditCard method works when the interestRate and creditLimit do not meet the condition")
    public void createCreditCard_Test2(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle primera","Madrid");
        AccountHolder accountHolder1 = new AccountHolder("Coco",accounts,address,"1234", LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreditCardDTO creditCardDTO = new CreditCardDTO(BigDecimal.valueOf(200.00).setScale(2), 10, LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.05).setScale(2),BigDecimal.valueOf(50));
        CreditCard result = null;
        try {
            result = creditCardService.createCreditCard(creditCardDTO);
        }catch (Exception e) {
            System.err.println(e);
        }

        assertThrows(IllegalArgumentException.class, () -> {creditCardService.createCreditCard(creditCardDTO);});


    }

}


