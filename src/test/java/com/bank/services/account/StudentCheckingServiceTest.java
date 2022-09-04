package com.bank.services.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.enums.Status;
import com.bank.models.account.Account;
import com.bank.models.account.StudentChecking;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import com.bank.repositories.user.AccountHolderRepository;
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
public class StudentCheckingServiceTest {
    @Autowired
    StudentCheckingService studentCheckingService;
    @Autowired
    AccountHolderRepository accountHolderRepository;



    @Test
    @DisplayName("Test that the createStudentChecking method of the StudentCheckingService works")
    public void createStudentChecking_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        LocalDate date = LocalDate.of(2001,1,1);
        AccountHolder accountHolder = new AccountHolder("pepe","1234",address, date);
        accountHolderRepository.save(accountHolder);
        CreateAccountDTO createAccountDTO = new CreateAccountDTO(new BigDecimal(50).setScale(2),LocalDate.now(), Status.ACTIVE,accountHolder,"1234");
        StudentChecking result = studentCheckingService.createStudentChecking(createAccountDTO);

        assertEquals(createAccountDTO.getPrimaryOwner().getName(), result.getPrimaryOwner().getName());

    }
}
