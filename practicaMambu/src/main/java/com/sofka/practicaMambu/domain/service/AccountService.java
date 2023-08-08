package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.AccountCreateResponseDTO;
import com.sofka.practicaMambu.domain.model.Account;

public interface AccountService {

    AccountCreateResponseDTO createAccount(Account account);
}
