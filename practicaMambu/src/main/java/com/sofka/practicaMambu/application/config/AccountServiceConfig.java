package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.AccountService;
import com.sofka.practicaMambu.infraestructure.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountServiceConfig {

    @Bean
    public AccountService accountService(){ return new AccountRepository();
    }

}
