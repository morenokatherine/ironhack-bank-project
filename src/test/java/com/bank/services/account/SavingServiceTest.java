package com.bank.services.account;

import com.bank.dto.account.CreateSavingDTO;
import com.bank.enums.Status;
import com.bank.models.account.Saving;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import com.bank.repositories.user.AccountHolderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;

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
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe","1234",address,LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreateSavingDTO savingDTO = new CreateSavingDTO(BigDecimal.valueOf(200.00).setScale(2), LocalDate.now(), Status.ACTIVE,accountHolder1, BigDecimal.valueOf(0.40).setScale(2),"123");
        Saving result = null;
        try {
            result = savingService.createSaving(savingDTO);
        }catch (Exception e) {
            System.err.println(e);
        }
        assertEquals(savingDTO.getBalance().subtract(BigDecimal.valueOf(result.getPenaltyFee())), result.getBalance());
    }

    @Test
    @DisplayName("Test that createSaving method exception works")
    public void createSaving_Test2() {
        Address address = new Address("Calle","Barcelona");
        AccountHolder accountHolder1 = new AccountHolder("pepe","1234",address,LocalDate.now());
        accountHolderRepository.save(accountHolder1);
        CreateSavingDTO savingDTO = new CreateSavingDTO(BigDecimal.valueOf(50).setScale(2), LocalDate.now(), Status.ACTIVE,accountHolder1,null,"123");

        assertThrows(Exception.class, () -> {savingService.createSaving(savingDTO);});
    }
}
