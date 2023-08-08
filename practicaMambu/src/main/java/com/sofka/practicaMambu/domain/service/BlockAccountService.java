package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.BlockAccountResponseDTO;
import com.sofka.practicaMambu.domain.model.BlockAccount;

public interface BlockAccountService {

    BlockAccountResponseDTO blockAccount(BlockAccount blockAccount, String depositAccountId);
}
