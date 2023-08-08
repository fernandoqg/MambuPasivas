package com.sofka.practicaMambu.infraestructure;

import com.sofka.practicaMambu.domain.dto.AccountCreateResponseDTO;
import com.sofka.practicaMambu.domain.dto.ApplyInterestResponseDTO;
import com.sofka.practicaMambu.domain.model.Account;
import com.sofka.practicaMambu.domain.model.ApplyInterest;
import com.sofka.practicaMambu.domain.service.ApplyInterestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.UUID;

@Service
public class ApplyInterestRepository implements ApplyInterestService {

    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Value("${spring.user}")
    private String user;

    @Value("${spring.password}")
    private String password;
    @Override
    public ApplyInterestResponseDTO apply(ApplyInterest applyInterest,String depositAccountId) {
        String credentials = user+":"+password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        RestTemplate restTemplate = new RestTemplate();

        UUID randomUUID = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic "+encodedCredentials);
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/vnd.mambu.v2+json");
        headers.add("Idempotency-Key",randomUUID.toString());

        HttpEntity<ApplyInterest> httpEntity = new HttpEntity<>(applyInterest,headers);

        ApplyInterestResponseDTO responseBody = null;

        try {
            ResponseEntity<ApplyInterestResponseDTO> response = restTemplate.exchange(basePath+"/deposits/"+depositAccountId+"/applyInterest", HttpMethod.POST, httpEntity, ApplyInterestResponseDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println(response.getStatusCode());

                responseBody.setDescription("INTERES APLICADO");

                responseBody = response.getBody();

            } else {
                System.out.println("La solicitud no fue exitosa. Código de estado: " + response.getStatusCode());
            }
            return responseBody;

        }catch (RestClientException e){
            System.err.println(e.getMessage());

            var errorCode = ((HttpStatusCodeException) e).getStatusCode();

            System.out.println("soy e.GETSTATUSCODE"+((HttpStatusCodeException) e).getStatusCode());
            System.out.println("soy respose.getstatuscode");

            return responseBody;
        }
    }
}
