package com.sofka.practicaMambu.application.config;

import com.sofka.practicaMambu.domain.service.BlockFundService;
import com.sofka.practicaMambu.infraestructure.BlockFundRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockFundConfig {
    @Bean
    public BlockFundService blockFund(){ return new BlockFundRepository();
    }
}
