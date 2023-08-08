package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.ApplyInterestResponseDTO;
import com.sofka.practicaMambu.domain.dto.BlockAccountResponseDTO;
import com.sofka.practicaMambu.domain.model.ApplyInterest;
import com.sofka.practicaMambu.domain.model.BlockAccount;
import com.sofka.practicaMambu.domain.service.ApplyInterestService;
import com.sofka.practicaMambu.domain.service.BlockAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/changeState")
public class BlockAccountController {

    @Autowired
    private BlockAccountService blockAccountService;

    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ApplyInterest> blockAccount(@RequestBody BlockAccount blockAccount, @PathVariable String depositAccountId){

        BlockAccountResponseDTO responseDTO = blockAccountService.blockAccount(blockAccount,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getAccountState());
        return responseEntity;
    }
}
