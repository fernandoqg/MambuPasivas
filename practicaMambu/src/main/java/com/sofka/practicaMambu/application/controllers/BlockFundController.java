package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.BlockFundResponseDTO;
import com.sofka.practicaMambu.domain.model.BlockFund;
import com.sofka.practicaMambu.domain.service.BlockFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocks")
public class BlockFundController {

    @Autowired
    private BlockFundService blockFundsService;
    @PostMapping("/{depositAccountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BlockFund> blockFund(@RequestBody BlockFund block, @PathVariable String depositAccountId){

        BlockFundResponseDTO responseDTO = blockFundsService.blockFund(block,depositAccountId);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, responseDTO.getStatusCode());
        System.out.println(responseDTO.getNotes() + " "+responseDTO.getAmount());
        return responseEntity;
    }



}
