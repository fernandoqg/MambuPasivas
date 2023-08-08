package com.sofka.practicaMambu.application.controllers;


import com.sofka.practicaMambu.domain.dto.SeizeBlockResponseDTO;
import com.sofka.practicaMambu.domain.dto.TransactionResponseDTO;
import com.sofka.practicaMambu.domain.model.SeizeBlockAmount;
import com.sofka.practicaMambu.domain.model.Transaction;
import com.sofka.practicaMambu.domain.service.SeizeBlockAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seize")
public class SeizeBlockController {

    @Autowired
    private SeizeBlockAmountService seizeBlockAmountService;

    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<SeizeBlockAmount> deposit(@RequestBody SeizeBlockAmount seizeBlockAmount, @PathVariable String depositAccountId){

        SeizeBlockResponseDTO responseDTO = seizeBlockAmountService.seize(seizeBlockAmount,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getType() +" "+responseDTO.getNotes());
        return responseEntity;
    }
}
