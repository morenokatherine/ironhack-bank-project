package com.bank.services.account;

import com.bank.dto.account.CreateSavingDTO;
import com.bank.models.account.Saving;
import com.bank.repositories.account.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class SavingService {
    @Autowired
    SavingRepository savingRepository;

    public Saving createSaving(CreateSavingDTO createSavingDTO) {

        Saving saving = new Saving();
        saving.setBalance(createSavingDTO.getBalance());
        saving.setDate(createSavingDTO.getDate());
        saving.setStatus(createSavingDTO.getStatus());
        saving.setPrimaryOwner(createSavingDTO.getPrimaryOwner());
        saving.setInterestRate(createSavingDTO.getInterestRate());
        saving.setSecretKey(createSavingDTO.getSecretKey());

        if (saving.getBalance().compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new IllegalArgumentException("Balance must be greater than 100");
        }
        return savingRepository.save(saving);
    }
}
