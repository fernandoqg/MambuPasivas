package com.sofka.practicaMambu.domain.model;

public class OnboardingClient {

    private Client client;
    private Account account;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
