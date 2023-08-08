package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.OnboardingClientService;
import com.sofka.practicaMambu.domain.service.ProductService;
import com.sofka.practicaMambu.infraestructure.OnboardingClientRepository;
import com.sofka.practicaMambu.infraestructure.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

    @Bean
    public ProductService product(){ return new ProductRepository();
    }
}
