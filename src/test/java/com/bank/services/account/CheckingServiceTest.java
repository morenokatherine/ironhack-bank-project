package com.bank.services.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.enums.Status;
import com.bank.models.account.Checking;
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
        Address address = new Address("Calle","Barcelona");
        LocalDate date = LocalDate.of(1995,1,1);
        AccountHolder accountHolder = new AccountHolder("pepe","1234",address,date);
        accountHolderRepository.save(accountHolder);
        CreateAccountDTO createAccountDTO = new CreateAccountDTO(new BigDecimal(50).setScale(2),LocalDate.now(), Status.ACTIVE,accountHolder,"1234");
        Checking result = checkingService.createChecking(createAccountDTO);

        assertEquals(BigDecimal.valueOf(12).setScale(2),result.getMonthlyMaintenanceFee());
        assertEquals(BigDecimal.valueOf(250).setScale(2),result.getMinimumBalance());

    }
}




