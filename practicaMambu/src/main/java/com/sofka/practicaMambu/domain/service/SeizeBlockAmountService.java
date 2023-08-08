package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.SeizeBlockResponseDTO;
import com.sofka.practicaMambu.domain.model.SeizeBlockAmount;

public interface SeizeBlockAmountService {

    SeizeBlockResponseDTO seize(SeizeBlockAmount seizeBlockAmount, String depositAccountId);
}
