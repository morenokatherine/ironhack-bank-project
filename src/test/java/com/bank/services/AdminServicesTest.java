package com.bank.services;

import com.bank.dto.AccountDTO;
import com.bank.dto.AccountHolderDTO;
import com.bank.dto.AdminDTO;
import com.bank.enums.Status;
import com.bank.models.account.Account;
import com.bank.models.account.Checking;
import com.bank.models.account.StudentChecking;
import com.bank.models.user.Admin;
import com.bank.models.utils.Address;
import com.bank.models.user.AccountHolder;
import com.bank.repositories.AccountHolderRepository;
import com.bank.services.account.CheckingService;
import com.bank.services.account.StudentCheckingService;
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
public class AdminServicesTest {
    @Autowired
    AdminService adminService;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    CheckingService checkingService;
    @Autowired
    StudentCheckingService studentCheckingService;


    @Test
    @DisplayName("Test that a new AccountHolder is created through the AdminService service by calling the createAccountHolder method")
    public void createAccountHolder_Test(){
        Address address = new Address("Calle","Barcelona");
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Pepe",address,"1234", LocalDate.now());
        AccountHolder result = adminService.createAccountHolder(accountHolderDTO);
        System.out.println(result.getId());

        assertEquals(1,result.getId());
        assertEquals("Pepe",result.getName());

    }

    @Test
    @DisplayName("Test that a new Admin is created through the AdminService service by calling the createAdmin method")
    public void createAdmin_Test(){
        AdminDTO admin = new AdminDTO("Administrador del banco");
       Admin result =  adminService.createAdmin(admin);

        assertEquals(admin.getName(), result.getName());



    }

    @Test
    @DisplayName("Test that the StudentChecking account is created via the createAccount method of the AdminService service if it meets the age condition > 24")
    public void createAccount_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        LocalDate date = LocalDate.of(1995,1,1);
        AccountHolder accountHolder = new AccountHolder("pepe",accounts,address,"1234",date);
        accountHolderRepository.save(accountHolder);
        AccountDTO accountDTO = new AccountDTO(new BigDecimal(50).setScale(2),10,LocalDate.now(), Status.ACTIVE,accountHolder,"1234");
        Account result = adminService.createAccount(accountDTO);

        //System.out.println(result.getClass());
        assertTrue(result instanceof Checking);

    }

    @Test
    @DisplayName("Test that StudentChecking account is created through the createAccount method of the AdminService service if it does NOT meet the condition of age > 24")
    public void createStudentChecking_Test(){
        List<Account> accounts = new ArrayList<>();
        Address address = new Address("Calle","Barcelona");
        LocalDate date = LocalDate.of(2001,1,1);
        AccountHolder accountHolder = new AccountHolder("pepe",accounts,address,"1234",date);
        accountHolderRepository.save(accountHolder);
        AccountDTO accountDTO = new AccountDTO(new BigDecimal(50).setScale(2),10,LocalDate.now(), Status.ACTIVE,accountHolder,"1234");
        Account result = adminService.createAccount(accountDTO);

        assertTrue(result instanceof StudentChecking);

    }
}


