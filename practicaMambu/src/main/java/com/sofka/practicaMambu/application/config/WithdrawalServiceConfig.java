package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.WithdrawalService;
import com.sofka.practicaMambu.infraestructure.WithdrawalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WithdrawalServiceConfig {

    @Bean
    public WithdrawalService withdrawalService(){ return new WithdrawalRepository();
    }
}
