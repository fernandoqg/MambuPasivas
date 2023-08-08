package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.SeizeBlockAmountService;
import com.sofka.practicaMambu.infraestructure.SeizeBlockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeizeBlockConfig {

    @Bean
    public SeizeBlockAmountService seize(){ return new SeizeBlockRepository();
    }
}
