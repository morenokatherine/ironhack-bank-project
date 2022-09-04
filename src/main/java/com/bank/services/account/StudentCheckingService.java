package com.bank.services.account;

import com.bank.dto.account.CreateAccountDTO;
import com.bank.models.account.StudentChecking;
import com.bank.repositories.account.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingService {
    @Autowired

    StudentCheckingRepository studentCheckingRepository;

    public StudentChecking createStudentChecking(CreateAccountDTO studentCheckingCreateAccountDTO){
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setBalance(studentCheckingCreateAccountDTO.getBalance());
        studentChecking.setDate(studentCheckingCreateAccountDTO.getDate());
        studentChecking.setStatus(studentCheckingCreateAccountDTO.getStatus());
        studentChecking.setPrimaryOwner(studentCheckingCreateAccountDTO.getPrimaryOwner());
        studentChecking.setSecretKey(studentCheckingCreateAccountDTO.getSecretKey());
        return studentCheckingRepository.save(studentChecking);
    }
}
