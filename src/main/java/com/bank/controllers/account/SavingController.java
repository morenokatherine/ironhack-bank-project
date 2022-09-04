package com.bank.controllers.account;

import com.bank.dto.account.CreateSavingDTO;
import com.bank.models.account.Saving;
import com.bank.services.account.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {

    @Autowired
    SavingService savingService;

    @PostMapping("/accounts/savings")
    public Saving createSavingAccount(@RequestBody CreateSavingDTO createSavingDTO) {
        return savingService.createSaving(createSavingDTO);
    }
}
