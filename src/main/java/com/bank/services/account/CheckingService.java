package com.bank.services.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.models.account.Checking;
import com.bank.repositories.account.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CheckingService {
    @Autowired
    CheckingRepository checkingRepository;

    public Checking createChecking(CreateAccountDTO checkingCreateAccountDTO){
        Checking checking = new Checking();
        checking.setBalance(checkingCreateAccountDTO.getBalance());
        checking.setDate(checkingCreateAccountDTO.getDate());
        checking.setStatus(checkingCreateAccountDTO.getStatus());
        checking.setPrimaryOwner(checkingCreateAccountDTO.getPrimaryOwner());
        checking.setSecretKey(checkingCreateAccountDTO.getSecretKey());
        return checkingRepository.save(checking);
    }

    public Checking getById(int id) {
        Optional<Checking> checking = checkingRepository.findById(id);
        if (!checking.isPresent()) {
            throw new IllegalArgumentException("account not found");
        }
        return checking.get();
    }
}
