package com.bank.services.account;

import com.bank.dto.AccountDTO;
import com.bank.enums.Status;
import com.bank.models.account.Account;
import com.bank.models.account.Checking;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckingServiceTest {
    @Autowired
    CheckingService checkingService;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Test
    @DisplayName("Test that the createChecking method of the CheckingService works." +
            "NOTE: Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12." +
            "Therefore, if the Checking Account was created correctly, it should have these properties")
    public void createChecking_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        LocalDate date = LocalDate.of(1995,1,1);
        AccountHolder accountHolder = new AccountHolder("pepe",accounts,address,"1234",date);
        accountHolderRepository.save(accountHolder);
        AccountDTO accountDTO = new AccountDTO(new BigDecimal(50).setScale(2),10,LocalDate.now(), Status.ACTIVE,accountHolder,"1234");
        Checking result = checkingService.createChecking(accountDTO);

        assertEquals(BigDecimal.valueOf(12),result.getMonthlyMaintenanceFee());
        assertEquals(BigDecimal.valueOf(250),result.getMinimumBalance());

    }
}




