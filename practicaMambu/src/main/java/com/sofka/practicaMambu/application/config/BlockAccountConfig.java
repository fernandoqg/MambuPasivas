package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.BlockAccountService;
import com.sofka.practicaMambu.domain.service.BlockFundService;
import com.sofka.practicaMambu.infraestructure.BlockAccountRepository;
import com.sofka.practicaMambu.infraestructure.BlockFundRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockAccountConfig {

    @Bean
    public BlockAccountService blockAccount(){ return new BlockAccountRepository();
    }
}
