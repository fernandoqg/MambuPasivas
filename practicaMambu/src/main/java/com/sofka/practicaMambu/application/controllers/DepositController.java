package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.TransactionResponseDTO;
import com.sofka.practicaMambu.domain.model.Transaction;
import com.sofka.practicaMambu.domain.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;
    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction deposit, @PathVariable String depositAccountId){

        TransactionResponseDTO responseDTO = depositService.deposit(deposit,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getAmount() +" "+ responseDTO.getType());
        return responseEntity;
    }


}
