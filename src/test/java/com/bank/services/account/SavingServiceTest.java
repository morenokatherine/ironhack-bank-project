package com.bank.services.account;

import com.bank.dto.SavingsDTO;
import com.bank.enums.Status;
import com.bank.models.account.Account;
import com.bank.models.account.Saving;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import com.bank.repositories.AccountHolderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_DOWN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SavingServiceTest {
    @Autowired
    SavingService savingService;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Test
    @DisplayName("Test the createSaving method")
    public void createSaving_Test() {
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe",accounts,address,"1234",LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        SavingsDTO savingDTO = new SavingsDTO(BigDecimal.valueOf(200.00).setScale(2), 10, LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.40).setScale(2),"123");
        Saving result = null;
        try {
            result = savingService.createSaving(savingDTO);
        }catch (Exception e) {
            System.err.println(e);
        }
        //assertTrue(savingDTO.equals(result));
        assertEquals(savingDTO.getBalance(), result.getBalance());
        assertEquals(1, result.getId());
    }

    @Test
    @DisplayName("Test that createSaving method exception works")
    public void createSaving_Test2() {
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe",accounts,address,"1234",LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        SavingsDTO savingDTO = new SavingsDTO(new BigDecimal(50.00).setScale(2, HALF_DOWN), 10, LocalDate.now(), Status.ACTIVE,accountHolder1,null,"123");

        assertThrows(Exception.class, () -> {savingService.createSaving(savingDTO);});
    }
}
