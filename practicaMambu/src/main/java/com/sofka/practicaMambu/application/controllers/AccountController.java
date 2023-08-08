package com.sofka.practicaMambu.application.controllers;


import com.sofka.practicaMambu.domain.dto.AccountCreateResponseDTO;
import com.sofka.practicaMambu.domain.model.Account;
import com.sofka.practicaMambu.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposits")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        AccountCreateResponseDTO responseDTO = accountService.createAccount(account);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getAccountState());
        return responseEntity;
    }
}
