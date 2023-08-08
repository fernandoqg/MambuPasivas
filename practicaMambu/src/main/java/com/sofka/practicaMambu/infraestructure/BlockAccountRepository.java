package com.sofka.practicaMambu.infraestructure;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sofka.practicaMambu.domain.dto.BlockAccountResponseDTO;
import com.sofka.practicaMambu.domain.model.BlockAccount;
import com.sofka.practicaMambu.domain.service.BlockAccountService;
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
public class BlockAccountRepository implements BlockAccountService {

    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Value("${spring.user}")
    private String user;

    @Value("${spring.password}")
    private String password;
    @Override
    public BlockAccountResponseDTO blockAccount(BlockAccount blockAccount, String depositAccountId) {
        String credentials = user + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        RestTemplate restTemplate = new RestTemplate();

        UUID randomUUID = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/vnd.mambu.v2+json");
        headers.add("Idempotency-Key", randomUUID.toString());

        HttpEntity<BlockAccount> httpEntity = new HttpEntity<>(blockAccount, headers);

        BlockAccountResponseDTO responseBody = null;

        BlockAccountResponseDTO createResponse;
        try {
            ResponseEntity<BlockAccountResponseDTO> response = restTemplate.exchange(basePath + "/deposits/" + depositAccountId +":changeState", HttpMethod.POST, httpEntity, BlockAccountResponseDTO.class);

            responseBody = response.getBody();
            responseBody.setStatusCode(response.getStatusCode());
            if(responseBody.getAccountState().equals("ACTIVE")){
                responseBody.setNotes("SE DESBLOQUEARON LOS FONDOS");

            }else{
                responseBody.setNotes("SE BLOQUEARON LOS FONDOS");
            }

            return responseBody;

        } catch (RestClientException e) {
            createResponse = new BlockAccountResponseDTO();
            createResponse.setStatusCode(((HttpStatusCodeException) e).getStatusCode());

            String jsonError = e instanceof HttpStatusCodeException ?
                    ((HttpStatusCodeException) e).getResponseBodyAsString() : "";

            if (!jsonError.isEmpty()) {
                jsonError = jsonError.substring(jsonError.indexOf("["), jsonError.indexOf("]") + 1);

                JsonParser parser = new JsonParser();

                JsonArray jsonArray = parser.parse(jsonError).getAsJsonArray();

                for (JsonElement element : jsonArray) {
                    JsonObject gsonObj = element.getAsJsonObject();

                    int errorCode = gsonObj.get("errorCode").getAsInt();

                    //String errorSource = gsonObj.get("errorSource").getAsString();
                    String errorReason = gsonObj.get("errorReason").getAsString();

                    createResponse.setErrorCode(errorCode);
                    //createResponse.setErrorSource(errorSource);
                    createResponse.setErrorReason(errorReason);
                }
                return createResponse;
            }
        }
        return createResponse;
    }
}
