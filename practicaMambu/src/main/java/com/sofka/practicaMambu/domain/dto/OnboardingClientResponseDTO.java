package com.sofka.practicaMambu.domain.dto;

public class OnboardingClientResponseDTO {

    private ClientCreateResponseDTO clientCreateResponseDTO;
    private AccountCreateResponseDTO accountCreateResponseDTO;

    public ClientCreateResponseDTO getClientCreateResponseDTO() {
        return clientCreateResponseDTO;
    }

    public void setClientCreateResponseDTO(ClientCreateResponseDTO clientCreateResponseDTO) {
        this.clientCreateResponseDTO = clientCreateResponseDTO;
    }

    public AccountCreateResponseDTO getAccountCreateResponseDTO() {
        return accountCreateResponseDTO;
    }

    public void setAccountCreateResponseDTO(AccountCreateResponseDTO accountCreateResponseDTO) {
        this.accountCreateResponseDTO = accountCreateResponseDTO;
    }
}
