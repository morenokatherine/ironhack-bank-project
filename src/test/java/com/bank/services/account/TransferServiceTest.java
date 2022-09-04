package com.bank.services.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.dto.user.CreateAccountHolderDTO;
import com.bank.dto.account.CreateSavingDTO;
import com.bank.dto.account.TransferDTO;
import com.bank.enums.Status;
import com.bank.models.account.Checking;
import com.bank.models.account.Saving;
import com.bank.models.account.Transfer;
import com.bank.models.user.AccountHolder;
import com.bank.models.utils.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransferServiceTest {
    @Autowired
    TransferService transferService;

    @Autowired
    CheckingService checkingService;

    @Autowired
    SavingService savingService;

    @Autowired
    AccountHolderService accountHolderService;

    @Test
    @DisplayName("Try the transfer method")
    void transfer_Test() {
        Address address = new Address("Calle primera","Madrid");
        CreateAccountHolderDTO accountHolderDTO1 = new CreateAccountHolderDTO("Coco",address,"1234", LocalDate.now());
        AccountHolder accountHolder1 = accountHolderService.createAccountHolder(accountHolderDTO1);
        CreateAccountDTO createAccountDTO1 = new CreateAccountDTO(BigDecimal.valueOf(1000), LocalDate.now(), Status.ACTIVE, accountHolder1, "");
        Checking checking1 = checkingService.createChecking(createAccountDTO1);

        CreateAccountHolderDTO accountHolderDTO2 = new CreateAccountHolderDTO("Ramona",address,"1234", LocalDate.now());
        AccountHolder accountHolder2 = accountHolderService.createAccountHolder(accountHolderDTO2);
        CreateSavingDTO accountDTO2 = new CreateSavingDTO(BigDecimal.valueOf(1000), LocalDate.now(), Status.ACTIVE, accountHolder2, BigDecimal.valueOf(0.5), "");
        Saving saving1 = savingService.createSaving(accountDTO2);


        TransferDTO transferDTO = new TransferDTO(checking1.getId(),saving1.getId(),800.00);
        Transfer transfer = transferService.transfer(transferDTO);

        checking1 = checkingService.getById(checking1.getId());
        assertEquals(BigDecimal.valueOf(160).setScale(2), checking1.getBalance());
    }
}
