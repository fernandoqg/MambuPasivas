package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.ApplyInterestService;
import com.sofka.practicaMambu.infraestructure.ApplyInterestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplyInterestConfig {

    @Bean
    public ApplyInterestService applyInterestService(){ return new ApplyInterestRepository();
    }
}
