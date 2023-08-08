package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.TransactionResponseDTO;
import com.sofka.practicaMambu.domain.model.Transaction;

public interface DepositService {

    TransactionResponseDTO deposit(Transaction deposit, String depositAccountId);
}
