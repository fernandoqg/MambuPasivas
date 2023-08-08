package com.sofka.practicaMambu.domain.service;

import com.sofka.practicaMambu.domain.dto.BlockFundResponseDTO;
import com.sofka.practicaMambu.domain.model.BlockFund;

import java.util.List;

public interface BlockFundService {

    BlockFundResponseDTO blockFund(BlockFund blockFunds, String depositAccountId);

}
