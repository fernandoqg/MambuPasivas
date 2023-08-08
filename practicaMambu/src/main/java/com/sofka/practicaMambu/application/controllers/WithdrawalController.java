package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.TransactionResponseDTO;
import com.sofka.practicaMambu.domain.model.Transaction;
import com.sofka.practicaMambu.domain.service.DepositService;
import com.sofka.practicaMambu.domain.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;
    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Transaction> withdrawal(@RequestBody Transaction transaction, @PathVariable String depositAccountId){

        TransactionResponseDTO responseDTO = withdrawalService.withdrawal(transaction,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getAmount() +" "+ responseDTO.getType());
        return responseEntity;
    }
}
