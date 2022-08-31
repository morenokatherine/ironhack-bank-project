package com.bank.services;

import com.bank.repositories.AccountHolderRepository;
import com.bank.repositories.AdminRepository;
import com.bank.repositories.ThirdPartyRepository;
import com.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

}
