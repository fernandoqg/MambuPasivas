package com.sofka.practicaMambu.application.controllers;

import com.sofka.practicaMambu.domain.dto.ClientCreateResponseDTO;
import com.sofka.practicaMambu.domain.dto.OnboardingClientResponseDTO;
import com.sofka.practicaMambu.domain.model.Client;
import com.sofka.practicaMambu.domain.model.OnboardingClient;
import com.sofka.practicaMambu.domain.service.OnboardingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingClientService onboardingClientService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> createClient(@RequestBody OnboardingClient onboardingClient){

        /*
        String flag = clientService.createClient(client).getFlag();

        if(flag.equals(HttpStatus.BAD_REQUEST)){
            ClientCreateResponseDTO responseDTO = clientService.createClient(client);
            ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.BAD_GATEWAY);
        }
        else{
            ClientCreateResponseDTO responseDTO = clientService.createClient(client);
            ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.OK);

        }

         */

        //System.out.println("Codigo Http "+HttpStatus.BAD_REQUEST);
        OnboardingClientResponseDTO responseDTO = onboardingClientService.onboardingClient(onboardingClient);
        ResponseEntity responseEntity = new ResponseEntity(responseDTO, HttpStatus.OK);
        return responseEntity;
    }
}
