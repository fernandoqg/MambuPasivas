package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.ApplyInterestResponseDTO;
import com.sofka.practicaMambu.domain.dto.TransactionResponseDTO;
import com.sofka.practicaMambu.domain.model.ApplyInterest;
import com.sofka.practicaMambu.domain.model.Transaction;
import com.sofka.practicaMambu.domain.service.ApplyInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applyInterest")
public class ApplyInterestController {

    @Autowired
    private ApplyInterestService applyInterestService;

    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ApplyInterest> applyInterest(@RequestBody ApplyInterest applyInterest, @PathVariable String depositAccountId){

        ApplyInterestResponseDTO responseDTO = applyInterestService.apply(applyInterest,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.OK);
        //System.out.println(responseDTO.getFirstName()+responseDTO.getLastName());
        return responseEntity;
    }
}
