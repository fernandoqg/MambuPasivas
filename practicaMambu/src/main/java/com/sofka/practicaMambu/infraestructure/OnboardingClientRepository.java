package com.sofka.practicaMambu.infraestructure;

import com.sofka.practicaMambu.domain.dto.AccountCreateResponseDTO;
import com.sofka.practicaMambu.domain.dto.ClientCreateResponseDTO;
import com.sofka.practicaMambu.domain.dto.OnboardingClientResponseDTO;
import com.sofka.practicaMambu.domain.model.Account;
import com.sofka.practicaMambu.domain.model.Client;
import com.sofka.practicaMambu.domain.model.OnboardingClient;
import com.sofka.practicaMambu.domain.service.OnboardingClientService;
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
public class OnboardingClientRepository implements OnboardingClientService {
    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Value("${spring.user}")
    private String user;

    @Value("${spring.password}")
    private String password;


    @Override
    public OnboardingClientResponseDTO onboardingClient(OnboardingClient onboardingClient) {

        String credentials = user+":"+password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        RestTemplate restTemplate = new RestTemplate();

        UUID randomUUID = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic "+encodedCredentials);
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/vnd.mambu.v2+json");
        headers.add("Idempotency-Key",randomUUID.toString());

        OnboardingClientResponseDTO onboardingResponse = new OnboardingClientResponseDTO();
        Client client = new Client();
        Account account = new Account();

        client.setFirstName(onboardingClient.getClient().getFirstName());
        client.setLastName(onboardingClient.getClient().getLastName());
        client.setPhoneNumber(onboardingClient.getClient().getPhoneNumber());
        client.setDocumentId(onboardingClient.getClient().getDocumentId());
        client.setEmail(onboardingClient.getClient().getEmail());
        client.setGender(onboardingClient.getClient().getGender());
        client.setAddress(onboardingClient.getClient().getAddress());
        client.setPreferredLanguage(onboardingClient.getClient().getPreferredLanguage());
        client.setPersonalizados(onboardingClient.getClient().getPersonalizados());

        HttpEntity<Client> httpEntityClient = new HttpEntity<>(client,headers);

        OnboardingClientResponseDTO responseBody = null;

        try {
            ResponseEntity<ClientCreateResponseDTO> responseClient = restTemplate.exchange(basePath+"/clients", HttpMethod.POST, httpEntityClient, ClientCreateResponseDTO.class);

            onboardingResponse.setClientCreateResponseDTO(responseClient.getBody());

            if (responseClient.getStatusCode().is2xxSuccessful()) {
                System.out.println(responseClient.getStatusCode());

                account.setAccountHolderKey(responseClient.getBody().getEncodedKey());
                account.setAccountHolderType(onboardingClient.getAccount().getAccountHolderType());
                account.setName(onboardingClient.getAccount().getName());
                account.setProductTypeKey(onboardingClient.getAccount().getProductTypeKey());
                account.setNotes(onboardingClient.getAccount().getNotes());
                account.setAccountState(onboardingClient.getAccount().getAccountState());
                account.setAccountType(onboardingClient.getAccount().getAccountType());
                account.setCurrencyCode(onboardingClient.getAccount().getCurrencyCode());

                HttpEntity<Account> httpEntityAccount = new HttpEntity<>(account,headers);


                ResponseEntity<AccountCreateResponseDTO> responseAccount = restTemplate.exchange(basePath+"/deposits", HttpMethod.POST, httpEntityAccount, AccountCreateResponseDTO.class);
                onboardingResponse.setAccountCreateResponseDTO(responseAccount.getBody());

                System.out.println("Respuesta: " + onboardingResponse);
            } else {
                System.out.println("La solicitud no fue exitosa. Código de estado: " + responseClient.getStatusCode());
            }
            return onboardingResponse;

        }catch (RestClientException e){
            System.err.println(e.getMessage());

            var errorCode = ((HttpStatusCodeException) e).getStatusCode();
            //responseBody.setFlag(((HttpStatusCodeException) e).getStatusCode().toString());
            System.out.println("soy e.GETSTATUSCODE"+((HttpStatusCodeException) e).getStatusCode());
            System.out.println("soy respose.getstatuscode");

            return onboardingResponse;
        }

    }
}
