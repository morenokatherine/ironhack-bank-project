package com.bank.services.account;

import com.bank.dto.account.ReceiveExternalTransferDTO;
import com.bank.dto.account.SendExternalTransferDTO;
import com.bank.dto.account.TransferDTO;
import com.bank.models.account.Account;
import com.bank.models.account.CreditCard;
import com.bank.models.account.Saving;
import com.bank.models.account.Transfer;
import com.bank.repositories.account.TransferRepository;
import com.bank.services.user.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ThirdPartyService thirdPartyService;





    public Transfer transfer(TransferDTO transferDTO) {
        Account originAccount = accountService.getAccountById(transferDTO.getOriginAccountId());
        Account destinationAccount = accountService.getAccountById(transferDTO.getDestinationAccountId());
        if (originAccount.getBalance().compareTo(BigDecimal.valueOf(transferDTO.getAmount())) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        BigDecimal destinationBalance = destinationAccount.getBalance().add(BigDecimal.valueOf(transferDTO.getAmount()));
        destinationAccount.setBalance(destinationBalance);
        BigDecimal originBalance = originAccount.getBalance().subtract(BigDecimal.valueOf(transferDTO.getAmount()));
        originAccount.setBalance(originBalance);



        accountService.saveAccount(originAccount);
        accountService.saveAccount(destinationAccount);

        Transfer transfer = new Transfer(originAccount, destinationAccount, BigDecimal.valueOf(transferDTO.getAmount()));
        return transferRepository.save(transfer);
    }

    public Transfer createExternalTransfer(SendExternalTransferDTO sendExternalTransferDTO) {

        Account account = accountService.getAccountById(sendExternalTransferDTO.getAccountId());
        if (account.getBalance().compareTo(BigDecimal.valueOf(sendExternalTransferDTO.getAmount())) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        BigDecimal accountBalance = account.getBalance().subtract(BigDecimal.valueOf(sendExternalTransferDTO.getAmount()));
        account.setBalance(accountBalance);
        accountService.saveAccount(account);

        Transfer transfer = new Transfer(account, null, BigDecimal.valueOf(sendExternalTransferDTO.getAmount()));
        return transferRepository.save(transfer);
    }

    public Transfer getExternalTransfer(String hashKey, ReceiveExternalTransferDTO receiveExternalTransferDTO) {

        thirdPartyService.getThirdPartyByHashKey(hashKey);

        Account account = accountService.getAccountById(receiveExternalTransferDTO.getAccountId());

        if (account instanceof CreditCard) {
            throw new IllegalArgumentException("You cannot transfer to a credit card account");
        }

        if (((Saving) account).getSecretKey() != receiveExternalTransferDTO.getSecretKey()){
            throw new IllegalArgumentException("secret key error");
        }

        BigDecimal accountBalance = account.getBalance().add(BigDecimal.valueOf(receiveExternalTransferDTO.getAmount()));
        account.setBalance(accountBalance);
        accountService.saveAccount(account);

        Transfer transfer = new Transfer(null, account, BigDecimal.valueOf(receiveExternalTransferDTO.getAmount()));
        return transferRepository.save(transfer);
    }

}
