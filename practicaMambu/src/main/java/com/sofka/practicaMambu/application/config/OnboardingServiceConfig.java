package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.DepositService;
import com.sofka.practicaMambu.domain.service.OnboardingClientService;
import com.sofka.practicaMambu.infraestructure.DepositRepository;
import com.sofka.practicaMambu.infraestructure.OnboardingClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnboardingServiceConfig {

    @Bean
    public OnboardingClientService onboardingClient(){ return new OnboardingClientRepository();
    }
}
