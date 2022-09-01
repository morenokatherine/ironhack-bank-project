package com.bank.services.account;

import com.bank.dto.SavingsDTO;
import com.bank.models.account.Saving;
import com.bank.repositories.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SavingService {
    @Autowired
    SavingRepository savingRepository;

    public Saving createSaving(SavingsDTO savingsDTO) {

        Saving saving = new Saving();
        saving.setBalance(savingsDTO.getBalance());
        saving.setPenaltyFree(savingsDTO.getPenaltyFree());
        saving.setDate(savingsDTO.getDate());
        saving.setStatus(savingsDTO.getStatus());
        saving.setPrimaryOwner(saving.getPrimaryOwner());
        saving.setInterestRate(savingsDTO.getInterestRate());
        saving.setSecretKey(savingsDTO.getSecretKey());

        if (saving.getBalance().compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new IllegalArgumentException("El balance debe ser mayor que 100");
        }
        return savingRepository.save(saving);
    }
}
