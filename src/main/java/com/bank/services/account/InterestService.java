package com.bank.services.account;

import com.bank.models.account.Account;
import com.bank.models.account.Interest;
import com.bank.repositories.account.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;

    public void addInterest(Account account) {
        Interest interest = new Interest(account);
        interestRepository.save(interest);
    }

    public List<Interest> getInterestsByAccount(Account account) {
        return interestRepository.findByAccount(account);
    }
}
