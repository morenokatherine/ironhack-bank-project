package com.bank.services.account;

import com.bank.dto.account.CreateCreditCardDTO;
import com.bank.enums.Status;
import com.bank.models.account.CreditCard;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import com.bank.repositories.user.AccountHolderRepository;
import com.bank.services.user.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    CreditCardService creditCardService;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Test
    @DisplayName("Test the createCreditCard method")
    public void createCreditCard_Test(){
        Address address = new Address("Calle primera","Madrid");
        AccountHolder accountHolder1 = new AccountHolder("Coco","1234",address, LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreateCreditCardDTO createCreditCardDTO = new CreateCreditCardDTO(BigDecimal.valueOf(200.00).setScale(2), 10, LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.5).setScale(2),BigDecimal.valueOf(200));
        CreditCard result = null;
        try {
           result = creditCardService.createCreditCard(createCreditCardDTO);
        }catch (Exception e) {
            System.err.println(e);
        }

       assertTrue(createCreditCardDTO.getPrimaryOwner().equals(result.getPrimaryOwner()));


    }

    @Test
    @DisplayName("Test that the exception of the createCreditCard method works when the interestRate and creditLimit do not meet the condition")
    public void createCreditCard_Test2(){
        Address address = new Address("Calle primera","Madrid");
        AccountHolder accountHolder1 = new AccountHolder("Coco","1234",address, LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreateCreditCardDTO createCreditCardDTO = new CreateCreditCardDTO(BigDecimal.valueOf(200.00).setScale(2), 10, LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.05).setScale(2),BigDecimal.valueOf(50));
        CreditCard result = null;
        try {
            result = creditCardService.createCreditCard(createCreditCardDTO);
        }catch (Exception e) {
            System.err.println(e);
        }

        assertThrows(IllegalArgumentException.class, () -> {creditCardService.createCreditCard(createCreditCardDTO);});


    }

}


