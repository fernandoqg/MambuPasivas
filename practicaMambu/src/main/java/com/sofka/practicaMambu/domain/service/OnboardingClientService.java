package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.OnboardingClientResponseDTO;
import com.sofka.practicaMambu.domain.model.OnboardingClient;

public interface OnboardingClientService {

    OnboardingClientResponseDTO onboardingClient(OnboardingClient onboardingClient);
}
