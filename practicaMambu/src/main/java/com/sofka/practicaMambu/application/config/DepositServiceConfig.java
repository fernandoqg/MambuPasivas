package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.DepositService;
import com.sofka.practicaMambu.infraestructure.DepositRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepositServiceConfig {
    @Bean
    public DepositService depositService(){ return new DepositRepository();
    }
}
