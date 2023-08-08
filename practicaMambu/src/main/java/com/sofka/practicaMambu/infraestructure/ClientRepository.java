package com.sofka.practicaMambu.infraestructure;


import com.sofka.practicaMambu.domain.dto.ClientCreateResponseDTO;
import com.sofka.practicaMambu.domain.model.Client;
import com.sofka.practicaMambu.domain.service.ClientService;
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
public class ClientRepository implements ClientService {

    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Value("${spring.user}")
    private String user;

    @Value("${spring.password}")
    private String password;

    @Override
    public ClientCreateResponseDTO createClient(Client client){

        String credentials = user+":"+password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        RestTemplate restTemplate = new RestTemplate();

        UUID randomUUID = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic "+encodedCredentials);
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/vnd.mambu.v2+json");
        headers.add("Idempotency-Key",randomUUID.toString());

        HttpEntity<Client> httpEntity = new HttpEntity<>(client,headers);
        ClientCreateResponseDTO responseBody = new ClientCreateResponseDTO();
        try {
            ResponseEntity<ClientCreateResponseDTO> response = restTemplate.exchange(basePath+"/clients", HttpMethod.POST, httpEntity, ClientCreateResponseDTO.class);


            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println();

                responseBody = response.getBody();
                //responseBody.setFlag(response.getStatusCode().toString());
                responseBody.setStatusCode(response.getStatusCode());

                System.out.println("Respuesta: " + responseBody);
            } else {
                System.out.println("La solicitud no fue exitosa. Código de estado: " + response.getStatusCode());
            }
            return responseBody;

        }catch (RestClientException e){
            System.err.println(e.getMessage());

            var errorCode = ((HttpStatusCodeException) e).getStatusCode();
            responseBody.setStatusCode(errorCode);
/*
            assert responseBody != null;


            String jsonError = e instanceof HttpStatusCodeException ?
                    ((HttpStatusCodeException)e).getResponseBodyAsString():"";
            System.out.println("JSONERROR "+ jsonError);


            JSONObject jsonTransform = null;
            try {
                jsonTransform = new JSONObject(jsonError);
            }catch (JSONException exception){
                exception.printStackTrace();
            }



            //jsonError = jsonError.substring(jsonError.indexOf("["), jsonError.indexOf("]")+1);

            //ErrorResponse responseError = new ErrorResponse();
            //System.out.println("Json: "+jsonError);
            //responseBody.setErrors();
            //responseBody.setFlag(((HttpStatusCodeException) e).getStatusCode().toString());

 */
            System.out.println("soy e.GETSTATUSCODE"+((HttpStatusCodeException) e).getStatusCode());
            System.out.println("soy respose.getstatuscode");

            //responseBody = response.getBody();

            return responseBody;
        }

    }
}
