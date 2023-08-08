package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.ApplyInterestResponseDTO;
import com.sofka.practicaMambu.domain.model.ApplyInterest;

public interface ApplyInterestService {

    ApplyInterestResponseDTO apply(ApplyInterest applyInterest,String accountID);
}
