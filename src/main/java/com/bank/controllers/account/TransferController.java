package com.bank.controllers.account;

import com.bank.dto.account.ReceiveExternalTransferDTO;
import com.bank.dto.account.SendExternalTransferDTO;
import com.bank.dto.account.TransferDTO;
import com.bank.models.account.Transfer;
import com.bank.services.account.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping("/transfers")
    public Transfer transfer(@RequestBody TransferDTO transferDTO) {
        return transferService.transfer(transferDTO);
    }

    @PostMapping("/transfers/external/send")
    public Transfer sendExternalTransfer(@RequestBody SendExternalTransferDTO sendExternalTransferDTO) {
        return transferService.createExternalTransfer(sendExternalTransferDTO);
    }

    @PostMapping("/transfers/external/receive")
    public Transfer receiveExternalTransfer(@RequestHeader("hash-key") String hashKey, @RequestBody ReceiveExternalTransferDTO receiveExternalTransferDTO) {
        return transferService.getExternalTransfer(hashKey, receiveExternalTransferDTO);
    }
}
