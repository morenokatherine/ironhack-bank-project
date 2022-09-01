package com.bank.services.account;

import com.bank.dto.AccountDTO;
import com.bank.models.account.Checking;
import com.bank.repositories.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingService {
    @Autowired
    CheckingRepository checkingRepository;


    public Checking createChecking(AccountDTO checkingAccountDTO){
        Checking checking = new Checking();
        checking.setBalance(checkingAccountDTO.getBalance());
        checking.setPenaltyFree(checkingAccountDTO.getPenaltyFree());
        checking.setDate(checkingAccountDTO.getDate());
        checking.setStatus(checkingAccountDTO.getStatus());
        checking.setPrimaryOwner(checkingAccountDTO.getPrimaryOwner());
        checking.setSecretKey(checkingAccountDTO.getSecretKey());
        return checkingRepository.save(checking);
    }
}
