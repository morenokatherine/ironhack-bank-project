package com.bank.services.account;

import com.bank.dto.AccountDTO;
import com.bank.models.account.StudentChecking;
import com.bank.repositories.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingService {
    @Autowired

    StudentCheckingRepository studentCheckingRepository;

    public StudentChecking createStudentChecking(AccountDTO studentCheckingAccountDTO){
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setBalance(studentCheckingAccountDTO.getBalance());
        studentChecking.setPenaltyFree(studentCheckingAccountDTO.getPenaltyFree());
        studentChecking.setDate(studentCheckingAccountDTO.getDate());
        studentChecking.setStatus(studentCheckingAccountDTO.getStatus());
        studentChecking.setPrimaryOwner(studentCheckingAccountDTO.getPrimaryOwner());
        studentChecking.setSecretKey(studentCheckingAccountDTO.getSecretKey());
        return studentCheckingRepository.save(studentChecking);
    }
}
